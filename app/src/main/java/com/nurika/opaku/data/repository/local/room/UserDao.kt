package com.nurika.opaku.data.repository.local.room

import androidx.paging.DataSource
import androidx.room.*
import com.nurika.opaku.data.repository.local.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg user: UserEntity)

    @Query("SELECT * FROM UserEntity")
    fun loadAllUser(): DataSource.Factory<Int, UserEntity>

    @Query("SELECT * FROM UserEntity WHERE userId = :mUserId")
    fun findById(mUserId: Int): UserEntity

    @Update
    fun updateUserRG(vararg user: UserEntity)

    @Delete
    fun deleteUserRG(vararg user: UserEntity)
}