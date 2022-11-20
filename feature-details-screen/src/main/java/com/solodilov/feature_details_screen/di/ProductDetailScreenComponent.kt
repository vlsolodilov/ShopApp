package com.solodilov.feature_details_screen.di

import com.solodilov.core.di.FeatureScope
import com.solodilov.feature_details_screen.presentation.fragment.ProductDetailFragment
import com.solodilov.feature_details_screen.presentation.fragment.ShopFragment
import dagger.Subcomponent

@FeatureScope
@Subcomponent(modules = [
    DataModule::class,
    ViewModelModule::class
])
interface ProductDetailScreenComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ProductDetailScreenComponent
    }

    fun inject(fragment: ProductDetailFragment)
    fun inject(fragment: ShopFragment)
}