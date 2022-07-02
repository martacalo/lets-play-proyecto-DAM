package com.martacalo.letsplay.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "library")
data class LibraryEntity(
    @PrimaryKey
    @ColumnInfo(name = "game_id")
    val gameId: String
)
