package com.solodilov.feature_main_screen.data.mapper

import com.solodilov.feature_main_screen.data.model.BestSellerDto
import com.solodilov.feature_main_screen.data.model.HotSaleDto
import com.solodilov.feature_main_screen.domain.entity.BestSeller
import com.solodilov.feature_main_screen.domain.entity.HotSale
import javax.inject.Inject

class ProductMapper @Inject constructor() {

    fun mapHotSaleDtoToHotSale(hotSaleDto: HotSaleDto): HotSale =
        HotSale(
            id = hotSaleDto.id,
            isNew = hotSaleDto.isNew ?: false,
            title = hotSaleDto.title,
            subtitle = hotSaleDto.subtitle,
            picture = hotSaleDto.picture,
        )

    fun mapBestSellerDtoToBestSeller(bestSellerDto: BestSellerDto): BestSeller =
        BestSeller(
            id = bestSellerDto.id,
            isFavorites = bestSellerDto.isFavorites,
            title = bestSellerDto.title,
            price = bestSellerDto.priceWithoutDiscount,
            fullPrice = bestSellerDto.discountPrice,
            picture = bestSellerDto.picture,
        )
}