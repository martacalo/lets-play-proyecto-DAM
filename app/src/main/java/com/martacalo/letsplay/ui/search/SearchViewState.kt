package com.martacalo.letsplay.ui.search

import com.martacalo.letsplay.core.ui.ViewState
import com.martacalo.letsplay.ui.search.model.Game

data class SearchViewState(
    val gamesListState: GamesListState = GamesListState.Loading,
    val message: String? = null,
): ViewState

sealed class GamesListState {
    object Loading: GamesListState()
    data class Success(val gamesList: List<Game>): GamesListState()
}
