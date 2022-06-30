package com.martacalo.letsplay.ui.search

import com.martacalo.letsplay.core.ui.ViewEvent

sealed class SearchViewEvent: ViewEvent {
    object OnInit: SearchViewEvent()
    data class OnSearch(val query: String): SearchViewEvent()
    data class OnSelectCoin(val coinId: String): SearchViewEvent()
}
