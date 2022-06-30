package com.martacalo.letsplay.core.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
abstract class BaseViewModel<VI : ViewIntent, VS : ViewState> : ViewModel() {

    private val initialState: VS by lazy { createInitialState() }

    private val viewIntentFlow = MutableSharedFlow<VI>()

    private val _viewStateFlow = MutableStateFlow(initialState)
    val state: StateFlow<VS>
        get() = _viewStateFlow

    abstract fun createInitialState(): VS

    abstract fun handleIntent(intent: VI): Flow<StateReducer<VS>>

    fun produceIntent(intent: VI) = viewModelScope.launch { viewIntentFlow.emit(intent) }

    init {
        viewModelScope.launch {
            viewIntentFlow
                .flatMapLatest { handleIntent(it) }
                .scan(initialState) { viewState, change -> change.reduce(viewState) }
                .onEach { setState(it) }
                .launchIn(viewModelScope)
        }
    }

    private fun setState(state: VS) {
        _viewStateFlow.value = state
    }

}
