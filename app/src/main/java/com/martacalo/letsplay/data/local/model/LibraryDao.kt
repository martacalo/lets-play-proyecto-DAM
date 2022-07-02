package com.martacalo.letsplay.data.local.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LibraryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(libraryEntity: LibraryEntity)

    @Transaction
    @Query("SELECT * FROM library")
    fun getGamesInLibrary(): Flow<List<GameAndLibrary>>
}
