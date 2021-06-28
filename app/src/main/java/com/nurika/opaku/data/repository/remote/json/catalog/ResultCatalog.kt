package com.nurika.opaku.data.repository.remote.json.catalog

import com.google.gson.annotations.SerializedName

data class ResultCatalog(
    @SerializedName("product_id")
    val productId: Int,
    @SerializedName("product_image")
    val productImage: String,
    @SerializedName("product_name")
    val productName: String?,
    @SerializedName("product_desc")
    val productDesc: String?,
    @SerializedName("product_price")
    val productPrice: Int,
    @SerializedName("product_sale")
    val productSale: Int,
    @SerializedName("product_discount_percent")
    val productDiscountPercent: Int
)