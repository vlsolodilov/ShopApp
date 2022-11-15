package com.solodilov.feature_cart_screen.data.mapper

import com.solodilov.feature_cart_screen.data.model.CartDto
import com.solodilov.feature_cart_screen.data.model.CartProductDto
import com.solodilov.feature_cart_screen.domain.entity.Cart
import com.solodilov.feature_cart_screen.domain.entity.CartProduct
import javax.inject.Inject

class CartMapper @Inject constructor() {

    private fun mapCartProductDtoToCartProduct(cartProductDto: CartProductDto): CartProduct =
        CartProduct(
            id = cartProductDto.id,
            title = cartProductDto.title,
            price = cartProductDto.price,
            images = cartProductDto.images,
        )

    fun mapCartDtoToCart(cartDto: CartDto): Cart =
        Cart(
            cartProductList = cartDto.cartListItemDto.map { mapCartProductDtoToCartProduct(it) },
            delivery = cartDto.delivery,
            total = cartDto.total,
        )
}