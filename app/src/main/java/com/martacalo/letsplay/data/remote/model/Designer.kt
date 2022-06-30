package com.martacalo.letsplay.data.remote.model

import com.martacalo.letsplay.data.local.model.DesignerEntity

data class Designer (
    val id: String,
    val name: String,
)

fun Designer.asDatabaseModel() = DesignerEntity(id, name)
