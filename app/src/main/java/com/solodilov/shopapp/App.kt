package com.solodilov.shopapp

import android.app.Application
import com.solodilov.feature_main_screen.di.MainScreenComponent
import com.solodilov.feature_main_screen.di.MainScreenComponentProvider
import com.solodilov.shopapp.di.DaggerApplicationComponent

class App : Application(), MainScreenComponentProvider {

    val appComponent = DaggerApplicationComponent.factory().create(this)

    override fun provideMainScreenComponent(): MainScreenComponent =
        appComponent.mainScreenComponent().create()
}