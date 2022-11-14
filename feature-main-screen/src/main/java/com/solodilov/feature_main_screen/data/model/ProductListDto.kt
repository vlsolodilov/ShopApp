package com.solodilov.feature_main_screen.data.model

import com.google.gson.annotations.SerializedName

data class ProductListDto(
    @SerializedName("home_store")
    val hotSaleDto: List<HotSaleDto>,
    @SerializedName("best_seller")
    val bestSellerDto: List<BestSellerDto>
)