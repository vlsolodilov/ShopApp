package com.solodilov.feature_details_screen.di

import com.solodilov.core.di.FeatureScope
import com.solodilov.feature_details_screen.data.datasource.ProductDetailDataSource
import com.solodilov.feature_details_screen.data.datasource.ProductDetailDataSourceImpl
import com.solodilov.feature_details_screen.data.network.ProductDetailApi
import com.solodilov.feature_details_screen.data.repository.ProductDetailRepositoryImpl
import com.solodilov.feature_details_screen.domain.repository.ProductDetailRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
interface DataModule {

    @Binds
    @FeatureScope
    fun bindProductDetailDataSource(impl: ProductDetailDataSourceImpl): ProductDetailDataSource

    @Binds
    @FeatureScope
    fun bindProductDetailRepository(impl: ProductDetailRepositoryImpl): ProductDetailRepository

    companion object {
        @Provides
        @FeatureScope
        fun provideProductDetailApi(retrofit: Retrofit) : ProductDetailApi =
            retrofit.create(ProductDetailApi::class.java)
    }

}