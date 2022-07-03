package com.martacalo.letsplay.ui.library.contract

import com.martacalo.letsplay.core.ui.StateReducer
import com.martacalo.letsplay.ui.library.contract.LibraryViewState
import com.martacalo.letsplay.ui.library.model.Library

sealed class LibraryStateReducer: StateReducer<LibraryViewState> {

    data class GetLibrary(
        val library: Library = Library(),
    ) : LibraryStateReducer() {
        override fun reduce(initialState: LibraryViewState): LibraryViewState =
            initialState.copy(library = library)
    }

}