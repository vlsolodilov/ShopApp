package com.solodilov.feature_main_screen.presentation.delegateAdapter.model

import com.solodilov.feature_main_screen.R

enum class Category(val title: String, val icon: Int) {
    PHONES("Phones", R.drawable.ic_phone),
    COMPUTER("Computer", R.drawable.ic_computer),
    HEALTH("Health", R.drawable.ic_health),
    BOOKS("Books", R.drawable.ic_books),
}