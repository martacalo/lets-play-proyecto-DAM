package com.martacalo.letsplay.data

import com.martacalo.letsplay.ui.library.model.Library
import kotlinx.coroutines.flow.Flow

interface LibraryRepository {
    suspend fun getLibrary(): Flow<Library>
}
