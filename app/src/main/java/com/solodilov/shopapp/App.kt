package com.solodilov.shopapp

import android.app.Application
import com.solodilov.feature_cart_screen.di.CartScreenComponent
import com.solodilov.feature_cart_screen.di.CartScreenComponentProvider
import com.solodilov.feature_main_screen.di.MainScreenComponent
import com.solodilov.feature_main_screen.di.MainScreenComponentProvider
import com.solodilov.shopapp.di.DaggerApplicationComponent

class App : Application(), MainScreenComponentProvider, CartScreenComponentProvider {

    val appComponent = DaggerApplicationComponent.factory().create(this)

    override fun provideMainScreenComponent(): MainScreenComponent =
        appComponent.mainScreenComponent().create()

    override fun provideCartScreenComponent(): CartScreenComponent =
        appComponent.cartScreenComponent().create()
}