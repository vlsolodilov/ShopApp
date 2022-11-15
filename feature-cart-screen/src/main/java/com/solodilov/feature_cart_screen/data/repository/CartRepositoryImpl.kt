package com.solodilov.feature_cart_screen.data.repository

import com.solodilov.feature_cart_screen.data.datasource.CartDataSource
import com.solodilov.feature_cart_screen.data.mapper.CartMapper
import com.solodilov.feature_cart_screen.domain.entity.Cart
import com.solodilov.feature_cart_screen.domain.repository.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val dataSource: CartDataSource,
    private val mapper: CartMapper,
) : CartRepository {

    override suspend fun getCart(): Cart =
        mapper.mapCartDtoToCart(dataSource.getCart())

}