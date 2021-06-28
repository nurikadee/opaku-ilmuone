package com.nurika.opaku.di

import android.content.Context
import com.nurika.opaku.ContextProviders
import com.nurika.opaku.data.repository.AppRepository
import com.nurika.opaku.data.repository.local.LocalDataSource
import com.nurika.opaku.data.repository.local.room.AppDatabase
import com.nurika.opaku.data.repository.remote.RemoteDataSource

object Injection {

    fun provideRepository(context: Context): AppRepository {

        val database = AppDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ContextProviders.getInstance())

        val localDataSource = LocalDataSource.getInstance(database.userDao(), database.catalogDao())

        return AppRepository.getInstance(remoteDataSource, localDataSource)
    }

}