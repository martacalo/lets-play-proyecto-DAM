package com.martacalo.letsplay.core.ui

interface BaseView<VS: ViewState> {
    fun renderState(state: VS)
}
