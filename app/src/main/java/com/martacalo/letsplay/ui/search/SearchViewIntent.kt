package com.martacalo.letsplay.ui.search

import com.martacalo.letsplay.core.ui.ViewIntent
import com.martacalo.letsplay.ui.search.model.Game

sealed class SearchViewIntent: ViewIntent {
    object OnInit: SearchViewIntent()
    data class OnSearch(val query: String): SearchViewIntent()
    data class OnSelectGame(val game: Game): SearchViewIntent()
}
