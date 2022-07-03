package com.martacalo.letsplay.data

import com.martacalo.letsplay.data.local.GamesDao
import com.martacalo.letsplay.data.local.model.asGameDetailsUIModel
import com.martacalo.letsplay.ui.gamedetails.model.GameDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GameDetailsRepositoryImpl @Inject constructor(
    private val gamesDao: GamesDao,
): GameDetailsRepository {
    override suspend fun getGame(id: String): Flow<GameDetails> =
        withContext(Dispatchers.IO) {
            gamesDao
                .getGame(id)
                .onEach {
                    val game = it
                    println(game)
                }
                .map { it.asGameDetailsUIModel() }
        }
}