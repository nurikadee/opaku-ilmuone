package com.nurika.opaku.data.repository.remote.json.user

import com.google.gson.annotations.SerializedName

data class ResultUser(
    @SerializedName("picture_url")
    val pictureUrl: String,
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("user_id")
    val userId: Int
)