package com.martacalo.letsplay.ui.library.contract

import com.martacalo.letsplay.core.ui.ViewIntent

sealed class LibraryViewIntent: ViewIntent {
    object OnGetLibrary: LibraryViewIntent()
}
