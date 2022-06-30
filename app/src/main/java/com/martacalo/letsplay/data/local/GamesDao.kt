package com.martacalo.letsplay.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.martacalo.letsplay.data.local.model.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GamesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(games: List<GameEntity>)

    @Query("SELECT id, name, small, medium, thumb, large, original, playtime, min_players, max_players, year_published FROM game")
    fun getAll(): Flow<List<GameEntity>>
}
