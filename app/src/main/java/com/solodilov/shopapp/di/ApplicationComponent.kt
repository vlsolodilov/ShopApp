package com.solodilov.shopapp.di

import android.app.Application
import com.solodilov.feature_cart_screen.di.CartScreenComponent
import com.solodilov.feature_main_screen.di.MainScreenComponent
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NetworkModule::class,
    SubcomponentsModule::class,
])
interface ApplicationComponent {

    fun retrofit(): Retrofit

    fun mainScreenComponent(): MainScreenComponent.Factory
    fun cartScreenComponent(): CartScreenComponent.Factory


    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application,
        ): ApplicationComponent
    }
}