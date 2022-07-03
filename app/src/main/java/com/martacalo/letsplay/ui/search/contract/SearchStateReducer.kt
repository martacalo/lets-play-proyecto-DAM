package com.martacalo.letsplay.ui.search.contract

import com.martacalo.letsplay.core.ui.StateReducer
import com.martacalo.letsplay.ui.search.model.Game

sealed class SearchStateReducer : StateReducer<SearchViewState> {

    object Init : SearchStateReducer() {
        override fun reduce(initialState: SearchViewState): SearchViewState = initialState.copy()
    }

    data class SaveGame(
        val game: Game,
    ): SearchStateReducer() {
        override fun reduce(initialState: SearchViewState): SearchViewState = initialState.copy()
    }

    class Search(
        val gamesList: List<Game> = listOf(),
        val errorMessage: String? = null,
    ) : SearchStateReducer() {
        override fun reduce(initialState: SearchViewState): SearchViewState =
            initialState.copy(
                gamesList = gamesList,
                message = errorMessage,
            )
    }

}
