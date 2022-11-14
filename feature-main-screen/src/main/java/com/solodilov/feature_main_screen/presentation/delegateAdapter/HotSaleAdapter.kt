package com.solodilov.feature_main_screen.presentation.delegateAdapter

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.solodilov.feature_main_screen.presentation.delegateAdapter.model.ItemUi

class HotSaleAdapter : AsyncListDifferDelegationAdapter<ItemUi>(BaseDiffUtilItemCallback()) {
    init {
        delegatesManager.addDelegate(MainScreenDelegates.hotSaleDelegate())
            .addDelegate(MainScreenDelegates.hotSaleProgressDelegate())
    }
}