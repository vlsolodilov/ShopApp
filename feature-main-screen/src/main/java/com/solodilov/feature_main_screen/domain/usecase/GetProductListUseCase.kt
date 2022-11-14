package com.solodilov.feature_main_screen.domain.usecase

import com.solodilov.feature_main_screen.domain.entity.ProductList
import com.solodilov.feature_main_screen.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(private val productRepository: ProductRepository) {

    suspend operator fun invoke(): ProductList = productRepository.getProductList()
}