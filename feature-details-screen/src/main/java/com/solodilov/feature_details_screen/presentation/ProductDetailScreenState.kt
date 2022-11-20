package com.solodilov.feature_details_screen.presentation

import com.solodilov.feature_details_screen.domain.entity.ProductDetail

sealed class ProductDetailScreenState {

    object Loading : ProductDetailScreenState()
    data class Content(val content: ProductDetail) : ProductDetailScreenState()
    object Error : ProductDetailScreenState()

}