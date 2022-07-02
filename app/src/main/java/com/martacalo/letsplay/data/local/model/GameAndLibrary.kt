package com.martacalo.letsplay.data.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class GameAndLibrary(
    @Embedded
    val library: LibraryEntity,
    @Relation(
        parentColumn = "game_id",
        entityColumn = "id"
    )
    val games: List<GameEntity>,
)
