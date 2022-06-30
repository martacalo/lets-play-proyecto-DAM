package com.martacalo.letsplay.data.remote.api

import com.martacalo.letsplay.data.remote.model.Search
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val atlasService: AtlasService,
): ApiHelper {

    override suspend fun search(): Response<Search> =
        atlasService.search()
}
