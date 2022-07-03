package com.martacalo.letsplay.ui.gamedetails.model

data class GameDetails (
    val id: String,
    val name: String,
    val images: Images,
    val playtime: String,
    val players: String,
    val yearPublished: Int,
)

