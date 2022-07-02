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

    private val _viewEffectFlow = MutableSharedFlow<ViewEffect>()
    val effect: SharedFlow<ViewEffect>
        get() = _viewEffectFlow

    abstract fun createInitialState(): VS

    abstract fun handleIntent(intent: VI): Flow<StateReducer<VS>>

    protected fun dispatchViewEffect(effect: ViewEffect) =
        viewModelScope.launch { _viewEffectFlow.emit(effect) }

    fun produceIntent(intent: VI) = viewModelScope.launch { viewIntentFlow.emit(intent) }

    protected open fun getViewEffectFromStateReducer(reducer: StateReducer<VS>): ViewEffect? = null

    init {
        viewModelScope.launch {
            viewIntentFlow
                .flatMapLatest { handleIntent(it) }
                .onEach { getViewEffectFromStateReducer(it)?.let(::dispatchViewEffect) }
                .scan(initialState) { viewState, change -> change.reduce(viewState) }
                .onEach { setState(it) }
                .launchIn(viewModelScope)
        }
    }

    private fun setState(state: VS) {
        _viewStateFlow.value = state
    }

}
