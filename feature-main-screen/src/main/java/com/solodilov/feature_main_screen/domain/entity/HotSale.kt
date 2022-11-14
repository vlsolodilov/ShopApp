package com.solodilov.feature_main_screen.domain.entity

data class HotSale(
    val id: Int,
    val isNew: Boolean,
    val title: String,
    val subtitle: String,
    val picture: String,
)
