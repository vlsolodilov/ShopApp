package com.solodilov.feature_main_screen.data.network

import com.solodilov.feature_main_screen.data.model.CartDto
import com.solodilov.feature_main_screen.data.model.ProductListDto
import retrofit2.http.GET

interface RunMockyApi {

    @GET("v3/654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getProductList(): ProductListDto

    @GET("v3/53539a72-3c5f-4f30-bbb1-6ca10d42c149")
    suspend fun getCart(): CartDto
}