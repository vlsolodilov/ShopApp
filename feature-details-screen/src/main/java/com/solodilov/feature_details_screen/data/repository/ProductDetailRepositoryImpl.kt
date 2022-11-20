package com.solodilov.feature_details_screen.data.repository

import com.solodilov.feature_details_screen.data.datasource.ProductDetailDataSource
import com.solodilov.feature_details_screen.data.mapper.ProductDetailMapper
import com.solodilov.feature_details_screen.domain.entity.ProductDetail
import com.solodilov.feature_details_screen.domain.repository.ProductDetailRepository
import javax.inject.Inject

class ProductDetailRepositoryImpl @Inject constructor(
    private val dataSource: ProductDetailDataSource,
    private val mapper: ProductDetailMapper,
) : ProductDetailRepository {

    override suspend fun getProductDetail(): ProductDetail =
        mapper.mapProductDetailDtoToProductDetail(dataSource.getProductDetail())

}