package com.solodilov.shopapp.di

import com.solodilov.feature_cart_screen.di.CartScreenComponent
import com.solodilov.feature_details_screen.di.ProductDetailScreenComponent
import com.solodilov.feature_main_screen.di.MainScreenComponent
import dagger.Module

@Module(subcomponents = [
    MainScreenComponent::class,
    CartScreenComponent::class,
    ProductDetailScreenComponent::class,
])
class SubcomponentsModule {
}