package com.solodilov.feature_cart_screen.data.network

import com.solodilov.feature_cart_screen.data.model.CartDto
import retrofit2.http.GET

interface CartApi {
    @GET("v3/53539a72-3c5f-4f30-bbb1-6ca10d42c149")
    suspend fun getCart(): CartDto
}