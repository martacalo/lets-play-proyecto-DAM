package com.martacalo.letsplay.ui.library

import com.martacalo.letsplay.core.ui.ViewState
import com.martacalo.letsplay.ui.library.model.Library

data class LibraryViewState(
    val library: Library,
): ViewState {
}