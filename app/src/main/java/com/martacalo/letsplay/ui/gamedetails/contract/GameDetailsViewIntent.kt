package com.martacalo.letsplay.ui.gamedetails.contract

import com.martacalo.letsplay.core.ui.ViewIntent

sealed class GameDetailsViewIntent: ViewIntent {
    data class GetGame(val id: String): GameDetailsViewIntent()
}
