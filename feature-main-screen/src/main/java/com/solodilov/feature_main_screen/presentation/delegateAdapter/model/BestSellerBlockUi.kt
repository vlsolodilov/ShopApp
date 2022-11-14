package com.solodilov.feature_main_screen.presentation.delegateAdapter.model

data class BestSellerBlockUi(
    val bestSellerList: List<ItemUi>
) : ItemUi {
    override val itemId: Long = 0
}
