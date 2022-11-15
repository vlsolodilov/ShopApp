package com.solodilov.feature_cart_screen.domain.usecase

import com.solodilov.feature_cart_screen.domain.entity.Cart
import com.solodilov.feature_cart_screen.domain.repository.CartRepository
import javax.inject.Inject

class GetCartUseCase @Inject constructor(private val cartRepository: CartRepository) {

    suspend operator fun invoke(): Cart =
        cartRepository.getCart()

}