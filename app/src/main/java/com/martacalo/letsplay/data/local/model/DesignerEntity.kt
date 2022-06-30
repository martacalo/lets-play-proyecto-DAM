package com.martacalo.letsplay.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "designer")
data class DesignerEntity(
    @PrimaryKey
    @ColumnInfo(name = "designer_id")
    val id: String,
    @ColumnInfo(name = "designer_name")
    val name: String,
)
