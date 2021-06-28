package com.nurika.opaku.data.repository.remote.json.catalog

import com.google.gson.annotations.SerializedName

data class CatalogApi(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<ResultCatalog>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)