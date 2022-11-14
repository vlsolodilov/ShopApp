package com.solodilov.feature_main_screen.data.datasource

import com.solodilov.feature_main_screen.data.model.ProductListDto

interface ProductDataSource {

    suspend fun getProductList(): ProductListDto
    suspend fun getCartSize(): Int
}