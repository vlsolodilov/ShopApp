package com.solodilov.feature_cart_screen.data.datasource

import com.solodilov.feature_cart_screen.data.model.CartDto

interface CartDataSource {
    suspend fun getCart(): CartDto
}