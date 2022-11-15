package com.solodilov.feature_cart_screen.data.model


import com.google.gson.annotations.SerializedName

data class CartProductDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("title")
    val title: String
)