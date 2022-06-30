package com.martacalo.letsplay.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.martacalo.letsplay.ui.search.model.Game
import com.martacalo.letsplay.ui.search.model.Images

@Entity(tableName = "game")
data class GameEntity(
    val commentary: String?,
    val description: String?,
    @ColumnInfo(name = "description_preview")
    val descriptionPreview: String?,
    // @Embedded
    // val designer: DesignerEntity?,
    @PrimaryKey
    val id: String,
    @Embedded
    val imagesEntity: ImagesEntity,
    @ColumnInfo(name = "max_players")
    val maxPlayers: Int?,
    @ColumnInfo(name = "max_playtime")
    val maxPlaytime: Int?,
    @ColumnInfo(name = "min_age")
    val minAge: Int?,
    @ColumnInfo(name = "min_players")
    val minPlayers: Int,
    @ColumnInfo(name = "min_playtime")
    val minPlaytime: Int?,
    val name: String,
    val playtime: String,
    val price: String?,
    val rank: Int?,
    @ColumnInfo(name = "thumb_url")
    val thumbUrl: String?,
    val visits: Int?,
    @ColumnInfo(name = "year_published")
    val yearPublished: Int
)

fun GameEntity.asUiModel() = Game(
    id,
    name,
    images = imagesEntity.toImage(),
    playtime,
    players = "$minPlayers - $maxPlayers ",
    yearPublished,
)

fun ImagesEntity.toImage() = Images(
    thumb = thumb,
    small = small,
    medium = medium,
)
