package com.martacalo.letsplay.ui.library.contract

sealed class LibraryNavigationEvent {
    object NavigateToSearch: LibraryNavigationEvent()
    data class NavigateToGame(val id: String): LibraryNavigationEvent()
}
