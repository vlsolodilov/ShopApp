package com.solodilov.feature_details_screen.domain.usecase

import com.solodilov.feature_details_screen.domain.entity.ProductDetail
import com.solodilov.feature_details_screen.domain.repository.ProductDetailRepository
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(private val repository: ProductDetailRepository) {

    suspend operator fun invoke(): ProductDetail =
        repository.getProductDetail()

}