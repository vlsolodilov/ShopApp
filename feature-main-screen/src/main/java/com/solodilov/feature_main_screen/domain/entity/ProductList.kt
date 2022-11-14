package com.solodilov.feature_main_screen.domain.entity

data class ProductList(
    val hotSaleList: List<HotSale>,
    val bestSellerList: List<BestSeller>
)
