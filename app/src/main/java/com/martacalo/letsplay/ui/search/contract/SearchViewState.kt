package com.martacalo.letsplay.ui.search.contract

import com.martacalo.letsplay.core.ui.ViewState
import com.martacalo.letsplay.ui.search.model.Game

data class SearchViewState(
    val gamesList: List<Game> = listOf(),
    val message: String? = null,
): ViewState
