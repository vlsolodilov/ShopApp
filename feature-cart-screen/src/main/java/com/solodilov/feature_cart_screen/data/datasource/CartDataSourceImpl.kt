package com.solodilov.feature_cart_screen.data.datasource

import com.solodilov.feature_cart_screen.data.model.CartDto
import com.solodilov.feature_cart_screen.data.network.CartApi
import javax.inject.Inject

class CartDataSourceImpl @Inject constructor(
    private val api: CartApi,
) : CartDataSource {

    override suspend fun getCart(): CartDto =
        api.getCart()
}