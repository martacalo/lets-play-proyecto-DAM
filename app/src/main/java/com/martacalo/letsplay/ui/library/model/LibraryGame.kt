package com.martacalo.letsplay.ui.library.model

import com.martacalo.letsplay.ui.search.model.Images

data class LibraryGame (
    val commentary: String?,
    val description: String?,
    val descriptionPreview: String?,
    val gameId: String,
    val id: String,
    val imagesEntity: Images,
    val maxPlayers: Int?,
    val maxPlaytime: Int?,
    val minAge: Int?,
    val minPlayers: Int,
    val minPlaytime: Int?,
    val name: String,
    val playtime: String,
    val price: String?,
    val rank: Int?,
    val thumbUrl: String?,
    val visits: Int?,
    val yearPublished: Int
)

