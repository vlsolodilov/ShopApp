package com.solodilov.feature_main_screen.presentation.delegateAdapter

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.solodilov.feature_main_screen.presentation.delegateAdapter.model.ItemUi

class MainScreenAdapter(
    onProductClick: (ItemUi) -> Unit,
    onRefreshClick: () -> Unit,
) : AsyncListDifferDelegationAdapter<ItemUi>(BaseDiffUtilItemCallback()) {
    init {
        delegatesManager
            .addDelegate(MainScreenDelegates.hotSaleBlockDelegate())
            .addDelegate(MainScreenDelegates.bestSellerBlockDelegate(onProductClick))
            .addDelegate(MainScreenDelegates.mainErrorDelegate(onRefreshClick))
    }
}