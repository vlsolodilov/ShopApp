package com.solodilov.feature_cart_screen.domain.entity

data class Cart(
    val cartProductList: List<CartProduct>,
    val delivery: String,
    val total: Int,
)