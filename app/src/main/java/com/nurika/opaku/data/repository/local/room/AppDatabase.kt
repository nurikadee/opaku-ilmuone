package com.nurika.opaku.data.repository.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nurika.opaku.data.repository.local.entity.CatalogEntity
import com.nurika.opaku.data.repository.local.entity.UserEntity

@Database(entities = [UserEntity::class, CatalogEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun catalogDao(): CatalogDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "Opaku.db"
                ).build()
            }
    }


}