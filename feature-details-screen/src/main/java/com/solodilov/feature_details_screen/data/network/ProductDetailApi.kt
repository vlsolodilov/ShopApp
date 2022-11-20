package com.solodilov.feature_details_screen.data.network

import com.solodilov.feature_details_screen.data.model.ProductDetailDto
import retrofit2.http.GET

interface ProductDetailApi {

    @GET("v3/6c14c560-15c6-4248-b9d2-b4508df7d4f5")
    suspend fun getProductDetail(): ProductDetailDto
}