package com.martacalo.letsplay.ui.search.contract

import com.martacalo.letsplay.core.ui.ViewEffect

sealed class SearchViewEffect : ViewEffect {
    data class Toast(val message: String): SearchViewEffect()
}
