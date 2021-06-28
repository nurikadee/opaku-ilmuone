package com.nurika.opaku.data.repository.local.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0,

    @ColumnInfo(name = "picture_url")
    var pictureUrl: String = "",

    @ColumnInfo(name = "full_name")
    var fullName: String = ""
)
