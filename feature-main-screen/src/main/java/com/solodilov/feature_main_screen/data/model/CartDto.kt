package com.solodilov.feature_main_screen.data.model


import com.google.gson.annotations.SerializedName

data class CartDto(
    @SerializedName("basket")
    val cartListItemDto: List<CartItemDto>,
    @SerializedName("delivery")
    val delivery: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("total")
    val total: Int
)