package com.nurika.opaku.data.repository.remote.api

import com.nurika.opaku.data.repository.remote.json.catalog.CatalogApi
import retrofit2.http.GET
import retrofit2.http.Query


interface CatalogService {

    @GET("catalog/list?")
    suspend fun fetchCatalog(
        @Query("api_key") apiKey: String,
        @Query("page") page: String
    ): CatalogApi
}