package com.martacalo.letsplay.ui.gamedetails.model

import com.martacalo.letsplay.ui.search.model.Images

data class GameDetails (
    val commentary: String = "",
    val description: String = "",
    val descriptionPreview: String = "",
    val gameId: String = "",
    val id: String = "",
    val imagesEntity: Images = Images("", "", ""),
    val maxPlayers: Int = 0,
    val maxPlaytime: Int = 0,
    val minAge: Int = 0,
    val minPlayers: Int = 0,
    val minPlaytime: Int = 0,
    val name: String = "",
    val playtime: String = "",
    val price: String = "",
    val rank: Int = 0,
    val thumbUrl: String = "",
    val visits: Int = 0,
    val yearPublished: Int = 0,
)

