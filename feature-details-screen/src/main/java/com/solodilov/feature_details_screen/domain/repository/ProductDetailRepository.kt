package com.solodilov.feature_details_screen.domain.repository

import com.solodilov.feature_details_screen.domain.entity.ProductDetail

interface ProductDetailRepository {
    suspend fun getProductDetail(): ProductDetail
}