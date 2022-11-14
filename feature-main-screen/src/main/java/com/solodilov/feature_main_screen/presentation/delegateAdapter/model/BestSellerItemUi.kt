package com.solodilov.feature_main_screen.presentation.delegateAdapter.model

data class BestSellerItemUi(
    val id: Int,
    val isFavorites: Boolean,
    val title: String,
    val price: String,
    val fullPrice: String,
    val picture: String,
) : ItemUi {
    override val itemId: Long = id.toLong()
}
