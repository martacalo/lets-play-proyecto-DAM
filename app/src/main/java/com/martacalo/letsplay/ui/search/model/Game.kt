package com.martacalo.letsplay.ui.search.model

data class Game (
    val id: String,
    val name: String,
    val images: Images,
    val playtime: String,
    val players: String,
    val yearPublished: Int,
)

