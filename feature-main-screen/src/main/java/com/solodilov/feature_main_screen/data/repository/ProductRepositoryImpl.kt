package com.solodilov.feature_main_screen.data.repository

import com.solodilov.feature_main_screen.data.datasource.ProductDataSource
import com.solodilov.feature_main_screen.data.mapper.ProductMapper
import com.solodilov.feature_main_screen.domain.entity.ProductList
import com.solodilov.feature_main_screen.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val dataSource: ProductDataSource,
    private val mapper: ProductMapper,
) : ProductRepository {

    override suspend fun getProductList(): ProductList {
        val productListDto = dataSource.getProductList()

        val hotSales = productListDto.hotSaleDto.map { hotSaleDto ->
            mapper.mapHotSaleDtoToHotSale(hotSaleDto)
        }
        val bestSellers = productListDto.bestSellerDto.map { bestSellerDto ->
            mapper.mapBestSellerDtoToBestSeller(bestSellerDto)
        }

        return ProductList(
            hotSaleList = hotSales,
            bestSellerList = bestSellers
        )
    }

    override suspend fun getCartSize(): Int =
        dataSource.getCartSize()

}