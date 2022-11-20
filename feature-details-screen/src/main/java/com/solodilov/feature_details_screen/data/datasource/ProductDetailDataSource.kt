package com.solodilov.feature_details_screen.data.datasource

import com.solodilov.feature_details_screen.data.model.ProductDetailDto

interface ProductDetailDataSource {
    suspend fun getProductDetail(): ProductDetailDto
}