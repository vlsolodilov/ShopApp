package com.solodilov.feature_main_screen.presentation.mapper

import com.solodilov.feature_main_screen.domain.entity.BestSeller
import com.solodilov.feature_main_screen.domain.entity.HotSale
import com.solodilov.feature_main_screen.presentation.delegateAdapter.model.BestSellerItemUi
import com.solodilov.feature_main_screen.presentation.delegateAdapter.model.HotSaleItemUi
import java.text.DecimalFormat
import javax.inject.Inject

class ProductUiMapper @Inject constructor() {

    fun mapHotSaleToHotSaleUi(hotSale: HotSale): HotSaleItemUi =
        HotSaleItemUi(
            id = hotSale.id,
            isNew = hotSale.isNew,
            title = hotSale.title,
            subtitle = hotSale.subtitle,
            picture = hotSale.picture,
        )

    fun mapBestSellerToBestSellerUi(bestSeller: BestSeller): BestSellerItemUi =
        BestSellerItemUi(
            id = bestSeller.id,
            isFavorites = bestSeller.isFavorites,
            title = bestSeller.title,
            price = formatPrice(bestSeller.price),
            fullPrice = formatPrice(bestSeller.fullPrice),
            picture = bestSeller.picture,
        )

    private fun formatPrice(price: Int): String =
        "$${DecimalFormat("#,###").format(price)}"
}