package com.solodilov.feature_details_screen.data.datasource

import com.solodilov.feature_details_screen.data.model.ProductDetailDto
import com.solodilov.feature_details_screen.data.network.ProductDetailApi
import javax.inject.Inject

class ProductDetailDataSourceImpl @Inject constructor(
    private val api: ProductDetailApi,
) : ProductDetailDataSource {

    override suspend fun getProductDetail(): ProductDetailDto =
        api.getProductDetail()
}