package com.nurika.opaku.data.repository.remote.api

import com.nurika.opaku.data.repository.remote.json.user.UserApi
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET("user/list?")
    suspend fun fetchUser(
        @Query("api_key") apiKey: String,
        @Query("page") page: String): UserApi
}