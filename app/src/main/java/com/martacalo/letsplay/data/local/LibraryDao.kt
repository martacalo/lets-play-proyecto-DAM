package com.martacalo.letsplay.data.local

import androidx.room.*
import com.martacalo.letsplay.data.local.model.GameEntity
import com.martacalo.letsplay.data.local.model.LibraryEntity
import com.martacalo.letsplay.ui.library.model.LibraryGame
import kotlinx.coroutines.flow.Flow

@Dao
interface LibraryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(libraryEntity: LibraryEntity)

    @Transaction
    @Query("SELECT * FROM library INNER JOIN game ON game_id=id")
    fun getGamesInLibrary(): Flow<List<GameEntity>>
}
