package com.martacalo.letsplay.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class ImagesEntity(
    @PrimaryKey
    val thumb: String,
    val small: String,
    val medium: String,
    val large: String,
    val original: String,
)
