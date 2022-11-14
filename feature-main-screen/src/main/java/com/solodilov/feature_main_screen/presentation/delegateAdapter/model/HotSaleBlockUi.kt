package com.solodilov.feature_main_screen.presentation.delegateAdapter.model

data class HotSaleBlockUi(
    val hotSaleList: List<ItemUi>
) : ItemUi {
    override val itemId: Long = 0
}
