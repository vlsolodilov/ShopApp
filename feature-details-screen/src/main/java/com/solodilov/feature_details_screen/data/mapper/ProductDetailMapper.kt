package com.solodilov.feature_details_screen.data.mapper

import com.solodilov.feature_details_screen.data.model.ProductDetailDto
import com.solodilov.feature_details_screen.domain.entity.ProductDetail
import javax.inject.Inject

class ProductDetailMapper @Inject constructor() {

    fun mapProductDetailDtoToProductDetail(productDetailDto: ProductDetailDto): ProductDetail =
        ProductDetail(
            cpu = productDetailDto.cpu,
            camera = productDetailDto.camera,
            capacity = productDetailDto.capacity,
            color = productDetailDto.color,
            id = productDetailDto.id,
            images = productDetailDto.images,
            isFavorites = productDetailDto.isFavorites,
            price = productDetailDto.price,
            rating = productDetailDto.rating,
            sd = productDetailDto.sd,
            ssd = productDetailDto.ssd,
            title = productDetailDto.title,
        )

}