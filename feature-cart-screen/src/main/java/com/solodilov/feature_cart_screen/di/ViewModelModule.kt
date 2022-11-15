package com.solodilov.feature_cart_screen.di

import androidx.lifecycle.ViewModel
import com.solodilov.core.di.ViewModelKey
import com.solodilov.feature_cart_screen.presentation.CartViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel::class)
    fun bindCartViewModel(viewModel: CartViewModel): ViewModel
}