package com.solodilov.feature_main_screen.presentation.delegateAdapter

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.solodilov.feature_main_screen.presentation.delegateAdapter.model.ItemUi

class BestSellerAdapter(
    onProductClick: (ItemUi) -> Unit,
) : AsyncListDifferDelegationAdapter<ItemUi>(BaseDiffUtilItemCallback()) {
    init {
        delegatesManager
            .addDelegate(MainScreenDelegates.bestSellerDelegate(onProductClick))
            .addDelegate(MainScreenDelegates.bestSellerProgressDelegate())
    }
}