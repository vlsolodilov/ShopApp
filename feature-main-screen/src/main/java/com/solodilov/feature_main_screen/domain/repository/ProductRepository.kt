package com.solodilov.feature_main_screen.domain.repository

import com.solodilov.feature_main_screen.domain.entity.ProductList

interface ProductRepository {

    suspend fun getProductList(): ProductList
    suspend fun getCartSize(): Int
}