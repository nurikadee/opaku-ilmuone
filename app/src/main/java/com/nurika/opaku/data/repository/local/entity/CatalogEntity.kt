package com.nurika.opaku.data.repository.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity
data class CatalogEntity(
    @PrimaryKey(autoGenerate = true)
    var productId: Int = 0,

    @ColumnInfo(name = "product_image")
    var productImage: String = "",

    @ColumnInfo(name = "product_name")
    var productName: String = "",

    @ColumnInfo(name = "product_desc")
    var productDesc: String = "",

    @ColumnInfo(name = "product_price")
    var productPrice: Int = 0,

    @ColumnInfo(name = "product_sale")
    var productSale: Int = 0,

    @ColumnInfo(name = "product_discount_percent")
    var productDiscountPercent: Int = 0,
) : Serializable