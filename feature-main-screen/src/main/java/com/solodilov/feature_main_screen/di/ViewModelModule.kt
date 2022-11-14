package com.solodilov.feature_main_screen.di

import androidx.lifecycle.ViewModel
import com.solodilov.core.di.ViewModelKey
import com.solodilov.feature_main_screen.presentation.MainScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainScreenViewModel::class)
    fun bindMainScreenViewModel(viewModel: MainScreenViewModel): ViewModel
}