package com.martacalo.letsplay.ui.gamedetails

import com.martacalo.letsplay.core.ui.BaseViewModel
import com.martacalo.letsplay.core.ui.StateReducer
import com.martacalo.letsplay.data.GameDetailsRepository
import com.martacalo.letsplay.ui.gamedetails.contract.GameDetailsStateReducer
import com.martacalo.letsplay.ui.gamedetails.contract.GameDetailsViewIntent
import com.martacalo.letsplay.ui.gamedetails.contract.GameDetailsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class GameDetailsViewModel @Inject constructor(
    private val gameDetailsRepository: GameDetailsRepository,
) : BaseViewModel<GameDetailsViewIntent, GameDetailsViewState>() {

    override fun createInitialState(): GameDetailsViewState = GameDetailsViewState()

    override fun handleIntent(intent: GameDetailsViewIntent): Flow<StateReducer<GameDetailsViewState>> {
        val getGameFlow = flowOf(intent)
            .filterIsInstance<GameDetailsViewIntent.GetGame>()
            .flatMapConcat {
                gameDetailsRepository
                    .getGame(it.id)
                    .map { GameDetailsStateReducer.GetGame(it) }
            }

        return merge(
            getGameFlow,
        )
    }

    fun getGame(id: String) {
        println(id)
        produceIntent(GameDetailsViewIntent.GetGame(id))
    }
}