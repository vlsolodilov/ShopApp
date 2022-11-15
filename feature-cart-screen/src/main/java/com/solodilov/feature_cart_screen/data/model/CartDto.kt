package com.solodilov.feature_cart_screen.data.model


import com.google.gson.annotations.SerializedName

data class CartDto(
    @SerializedName("basket")
    val cartListItemDto: List<CartProductDto>,
    @SerializedName("delivery")
    val delivery: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("total")
    val total: Int
)