package com.solodilov.feature_cart_screen.domain.repository

import com.solodilov.feature_cart_screen.domain.entity.Cart

interface CartRepository {
    suspend fun getCart(): Cart
}