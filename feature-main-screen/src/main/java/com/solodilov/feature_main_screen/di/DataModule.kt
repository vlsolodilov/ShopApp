package com.solodilov.feature_main_screen.di

import com.solodilov.core.di.FeatureScope
import com.solodilov.feature_main_screen.data.datasource.ProductDataSource
import com.solodilov.feature_main_screen.data.datasource.ProductDataSourceImpl
import com.solodilov.feature_main_screen.data.repository.ProductRepositoryImpl
import com.solodilov.feature_main_screen.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @Binds
    @FeatureScope
    fun bindProductDataSource(impl: ProductDataSourceImpl): ProductDataSource

    @Binds
    @FeatureScope
    fun bindProductRepository(impl: ProductRepositoryImpl): ProductRepository

}