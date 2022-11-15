package com.solodilov.feature_cart_screen.presentation

import com.solodilov.feature_cart_screen.domain.entity.Cart

sealed class CartScreenState {

    object Loading : CartScreenState()
    data class Content(val content: Cart) : CartScreenState()
    object Error : CartScreenState()

}