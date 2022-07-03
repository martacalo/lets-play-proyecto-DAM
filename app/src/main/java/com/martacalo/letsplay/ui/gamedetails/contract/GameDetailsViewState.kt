package com.martacalo.letsplay.ui.gamedetails.contract

import com.martacalo.letsplay.core.ui.ViewState
import com.martacalo.letsplay.ui.gamedetails.model.GameDetails

data class GameDetailsViewState(
    val gameDetails: GameDetails = GameDetails(),
): ViewState
