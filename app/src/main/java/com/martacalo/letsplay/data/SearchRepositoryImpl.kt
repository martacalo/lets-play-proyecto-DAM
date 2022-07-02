package com.martacalo.letsplay.data

import com.martacalo.letsplay.data.local.GamesDao
import com.martacalo.letsplay.data.local.model.LibraryDao
import com.martacalo.letsplay.data.local.model.LibraryEntity
import com.martacalo.letsplay.data.local.model.asUiModel
import com.martacalo.letsplay.data.remote.ResponseResult
import com.martacalo.letsplay.data.remote.api.ApiHelper
import com.martacalo.letsplay.data.remote.model.asDatabaseModel
import com.martacalo.letsplay.ui.search.model.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val gamesDao: GamesDao,
    private val libraryDao: LibraryDao,
) : SearchRepository {

    override suspend fun saveGame(id: String) = libraryDao.insert(LibraryEntity(gameId = id))

    override suspend fun search(query: String): Flow<ResponseResult<List<Game>>> =
        withContext(Dispatchers.IO) {
            gamesDao
                .search("%$query%")
                .distinctUntilChanged()
                .map { games -> games.map { it.asUiModel() } }
                .map { ResponseResult.Success(it) }
        }

    override suspend fun refreshGames() {
        withContext(Dispatchers.IO) {
            val response = apiHelper.search()
            val responseBody = apiHelper.search().body()
            if (response.isSuccessful && responseBody != null) {
                gamesDao.insertAll(responseBody.games.map { it.asDatabaseModel() })
            }
        }
    }

}
