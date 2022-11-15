package com.solodilov.feature_cart_screen.domain.entity

data class CartProduct(
    val id: Int,
    val images: String,
    val price: Int,
    val title: String,
    val quantity: Int = 1,
)