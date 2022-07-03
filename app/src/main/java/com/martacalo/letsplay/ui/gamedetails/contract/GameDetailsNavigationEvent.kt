package com.martacalo.letsplay.ui.gamedetails.contract

sealed class GameDetailsNavigationEvent {
    object NavigateBack: GameDetailsNavigationEvent()
}
