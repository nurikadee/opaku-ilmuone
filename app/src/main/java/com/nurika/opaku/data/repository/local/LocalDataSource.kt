package com.nurika.opaku.data.repository.local

import androidx.paging.DataSource
import com.nurika.opaku.data.repository.local.entity.CatalogEntity
import com.nurika.opaku.data.repository.local.entity.UserEntity
import com.nurika.opaku.data.repository.local.room.CatalogDao
import com.nurika.opaku.data.repository.local.room.UserDao

class LocalDataSource private constructor(
    private val userDao: UserDao,
    private val catalogDao: CatalogDao
) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(userDao: UserDao, catalogDao: CatalogDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(userDao, catalogDao)
    }

    //USER
    fun getAllUser(): DataSource.Factory<Int, UserEntity> = userDao.loadAllUser()

    fun insertUser(user: UserEntity) = userDao.insert(user)

    //CATALOG
    fun getAllCatalog(): DataSource.Factory<Int, CatalogEntity> = catalogDao.loadAllCatalog()

    fun insertCatalog(catalog: CatalogEntity) = catalogDao.insert(catalog)

}

