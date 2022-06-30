package com.martacalo.letsplay.data

import com.martacalo.letsplay.data.remote.ResponseResult
import com.martacalo.letsplay.ui.search.model.Game
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun search(query: String): Flow<ResponseResult<List<Game>>>
    suspend fun refreshGames()
}
