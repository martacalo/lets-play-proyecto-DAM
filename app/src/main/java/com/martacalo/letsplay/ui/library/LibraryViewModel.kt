package com.martacalo.letsplay.ui.library

import androidx.lifecycle.viewModelScope
import com.martacalo.letsplay.core.ui.BaseViewModel
import com.martacalo.letsplay.core.ui.StateReducer
import com.martacalo.letsplay.data.LibraryRepository
import com.martacalo.letsplay.data.SearchRepository
import com.martacalo.letsplay.ui.library.model.Library
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@FlowPreview
@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    private val libraryRepository: LibraryRepository,
) : BaseViewModel<LibraryViewIntent, LibraryViewState>() {

    init {
        viewModelScope.launch {
            searchRepository.refreshGames()
        }
    }

    override fun createInitialState(): LibraryViewState = LibraryViewState(Library())

    override fun handleIntent(intent: LibraryViewIntent): Flow<StateReducer<LibraryViewState>> {
        val libraryFlow = flowOf(intent)
            .filterIsInstance<LibraryViewIntent.OnGetLibrary>()
            .flatMapConcat {
                println("hello")
                libraryRepository.getLibrary()
            }
            .map { LibraryStateReducer.GetLibrary(it) }

        return merge(
            libraryFlow,
        )
    }

    fun loadLibrary() {
        produceIntent(LibraryViewIntent.OnGetLibrary)
    }

}
