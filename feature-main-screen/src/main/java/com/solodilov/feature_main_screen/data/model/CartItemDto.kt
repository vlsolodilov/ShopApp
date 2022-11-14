package com.solodilov.feature_main_screen.data.model


import com.google.gson.annotations.SerializedName

data class CartItemDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("title")
    val title: String
)