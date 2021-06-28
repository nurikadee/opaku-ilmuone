package com.nurika.opaku.data.repository.remote.json.user

import com.google.gson.annotations.SerializedName

data class UserApi(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<ResultUser>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)