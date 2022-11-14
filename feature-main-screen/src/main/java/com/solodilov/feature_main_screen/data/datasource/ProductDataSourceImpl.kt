package com.solodilov.feature_main_screen.data.datasource

import com.solodilov.feature_main_screen.data.model.ProductListDto
import com.solodilov.feature_main_screen.data.network.RunMockyApi
import javax.inject.Inject

class ProductDataSourceImpl @Inject constructor(
    private val api: RunMockyApi,
) : ProductDataSource {

    override suspend fun getProductList(): ProductListDto =
        api.getProductList()

    override suspend fun getCartSize(): Int =
        api.getCart().cartListItemDto.size

}