package com.nurika.opaku.data.repository.remote.json

import com.google.gson.GsonBuilder
import com.nurika.opaku.data.repository.remote.api.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    companion object {

        fun selectClass(strApi: String, selClass: Any): Any? {
            return when (selClass) {
                UserService::class.java ->
                    Retrofit.Builder()
                        .baseUrl(strApi)
                        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                        .build()
                        .create(selClass as Class<*>)

                else -> throw Exception("no class found")

            }
        }
    }



}