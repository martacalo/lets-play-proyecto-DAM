package com.martacalo.letsplay.data.remote.model

import com.martacalo.letsplay.data.local.model.GameEntity
import com.squareup.moshi.Json

data class Game (
    val commentary: String,
    val description: String,
    @Json(name = "description_preview")
    val descriptionPreview: String,
    val id: String,
    val images: Images,
    @Json(name = "max_players")
    val maxPlayers: Int,
    @Json(name = "max_playtime")
    val maxPlaytime: Int,
    @Json(name = "min_age")
    val minAge: Int,
    @Json(name = "min_players")
    val minPlayers: Int,
    @Json(name = "min_playtime")
    val minPlaytime: Int,
    val name: String,
    val playtime: String,
    val price: String,
    val rank: Int,
    @Json(name = "thumb_url")
    val thumbUrl: String,
    val visits: Int,
    @Json(name = "year_published")
    val yearPublished: Int,
)

fun Game.asDatabaseModel() = GameEntity(
    commentary,
    description,
    descriptionPreview,
    // designer?.asDatabaseModel(),
    id,
    images.asDatabaseModel(),
    maxPlayers,
    maxPlaytime,
    minAge,
    minPlayers,
    minPlaytime,
    name,
    playtime,
    price,
    rank,
    thumbUrl,
    visits,
    yearPublished,
)
