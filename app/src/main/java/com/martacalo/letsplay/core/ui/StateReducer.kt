package com.martacalo.letsplay.core.ui

interface StateReducer<VS: ViewState> {
    fun reduce(initialState: VS): VS
}
