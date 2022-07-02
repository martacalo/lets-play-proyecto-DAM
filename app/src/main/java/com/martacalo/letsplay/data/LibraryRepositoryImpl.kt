package com.martacalo.letsplay.data

import com.martacalo.letsplay.data.local.LibraryDao
import com.martacalo.letsplay.data.local.model.asLibraryUIModel
import com.martacalo.letsplay.data.local.model.asUiModel
import com.martacalo.letsplay.ui.library.model.Library
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LibraryRepositoryImpl @Inject constructor(
    private val libraryDao: LibraryDao,
) : LibraryRepository {

    override suspend fun getLibrary(): Flow<Library> =
        withContext(Dispatchers.IO) {
            libraryDao
                .getGamesInLibrary()
                .map { Library(games = it.map { it.asLibraryUIModel() }) }
        }

}
