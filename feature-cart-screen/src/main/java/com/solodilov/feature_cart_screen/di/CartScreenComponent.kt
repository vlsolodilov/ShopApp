package com.solodilov.feature_cart_screen.di

import com.solodilov.core.di.FeatureScope
import com.solodilov.feature_cart_screen.presentation.CartFragment
import dagger.Subcomponent

@FeatureScope
@Subcomponent(modules = [
    DataModule::class,
    ViewModelModule::class
])
interface CartScreenComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): CartScreenComponent
    }

    fun inject(fragment: CartFragment)
}