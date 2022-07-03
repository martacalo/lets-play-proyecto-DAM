package com.martacalo.letsplay.ui.gamedetails.contract

import com.martacalo.letsplay.core.ui.StateReducer
import com.martacalo.letsplay.ui.gamedetails.model.GameDetails

sealed class GameDetailsStateReducer: StateReducer<GameDetailsViewState> {

    data class GetGame(
        val gameDetails: GameDetails = GameDetails(),
    ) : GameDetailsStateReducer() {
        override fun reduce(initialState: GameDetailsViewState): GameDetailsViewState =
            initialState.copy(gameDetails = gameDetails)
    }
}
