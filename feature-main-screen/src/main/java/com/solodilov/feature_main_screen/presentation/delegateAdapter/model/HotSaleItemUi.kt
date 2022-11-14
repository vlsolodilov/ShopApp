package com.solodilov.feature_main_screen.presentation.delegateAdapter.model

data class HotSaleItemUi(
    val id: Int,
    val isNew: Boolean,
    val title: String,
    val subtitle: String,
    val picture: String,
) : ItemUi {
    override val itemId: Long = id.toLong()
}
