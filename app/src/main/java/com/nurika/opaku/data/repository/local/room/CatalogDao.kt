package com.nurika.opaku.data.repository.local.room

import androidx.paging.DataSource
import androidx.room.*
import com.nurika.opaku.data.repository.local.entity.CatalogEntity

@Dao
interface CatalogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg catalog: CatalogEntity)

    @Query("SELECT * FROM CatalogEntity")
    fun loadAllCatalog(): DataSource.Factory<Int, CatalogEntity>

    @Query("SELECT * FROM CatalogEntity WHERE productId = :productId")
    fun findById(productId: Int): CatalogEntity

    @Update
    fun updateCatalog(vararg catalog: CatalogEntity)

    @Delete
    fun deleteCatalog(vararg catalog: CatalogEntity)
}