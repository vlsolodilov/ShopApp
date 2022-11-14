package com.solodilov.feature_main_screen.domain.entity

data class BestSeller(
    val id: Int,
    val isFavorites: Boolean,
    val title: String,
    val price: Int,
    val fullPrice: Int,
    val picture: String
)
