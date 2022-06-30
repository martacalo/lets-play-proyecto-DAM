package com.martacalo.letsplay.data.remote.model

import com.martacalo.letsplay.data.local.model.ImagesEntity

data class Images(
    val thumb: String,
    val small: String,
    val medium: String,
    val large: String,
    val original: String,
)

fun Images.asDatabaseModel() = ImagesEntity(
    thumb,
    small,
    medium,
    large,
    original,
)
