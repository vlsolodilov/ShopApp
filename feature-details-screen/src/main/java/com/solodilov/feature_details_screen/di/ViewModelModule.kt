package com.solodilov.feature_details_screen.di

import androidx.lifecycle.ViewModel
import com.solodilov.core.di.ViewModelKey
import com.solodilov.feature_details_screen.presentation.ProductDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProductDetailViewModel::class)
    fun bindProductDetailViewModel(viewModel: ProductDetailViewModel): ViewModel
}