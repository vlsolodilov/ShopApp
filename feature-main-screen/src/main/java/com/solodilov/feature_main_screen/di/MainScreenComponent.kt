package com.solodilov.feature_main_screen.di

import com.solodilov.core.di.FeatureScope
import com.solodilov.feature_main_screen.presentation.MainFragment
import dagger.Subcomponent

@FeatureScope
@Subcomponent(modules = [
    DataModule::class,
    ViewModelModule::class
])
interface MainScreenComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainScreenComponent
    }

    fun inject(fragment: MainFragment)
}