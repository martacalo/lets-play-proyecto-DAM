package com.martacalo.letsplay.ui.search

import com.martacalo.letsplay.core.ui.ViewIntent

sealed class SearchViewIntent: ViewIntent {
    object OnInit: SearchViewIntent()
    data class OnSearch(val query: String): SearchViewIntent()
    data class OnSelectCoin(val coinId: String): SearchViewIntent()
}
