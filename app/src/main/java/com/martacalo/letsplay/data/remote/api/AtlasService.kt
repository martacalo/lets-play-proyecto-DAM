package com.martacalo.letsplay.data.remote.api

import com.martacalo.letsplay.data.remote.model.Search
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AtlasService {

    @GET("search?limit=100&client_id=JLBr5npPhV")
    suspend fun search(@Query("query") query: String = ""): Response<Search>

}
