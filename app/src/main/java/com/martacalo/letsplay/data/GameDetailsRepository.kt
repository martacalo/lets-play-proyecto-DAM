package com.martacalo.letsplay.data

import com.martacalo.letsplay.ui.gamedetails.model.GameDetails
import kotlinx.coroutines.flow.Flow

interface GameDetailsRepository {
    suspend fun getGame(id: String): Flow<GameDetails>
}