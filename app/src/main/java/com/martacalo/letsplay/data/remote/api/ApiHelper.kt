package com.martacalo.letsplay.data.remote.api

import com.martacalo.letsplay.data.remote.model.Search
import retrofit2.Response


interface ApiHelper {
    suspend fun search(): Response<Search>
}
