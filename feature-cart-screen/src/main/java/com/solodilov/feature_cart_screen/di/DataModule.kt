package com.solodilov.feature_cart_screen.di

import com.solodilov.core.di.FeatureScope
import com.solodilov.feature_cart_screen.data.datasource.CartDataSource
import com.solodilov.feature_cart_screen.data.datasource.CartDataSourceImpl
import com.solodilov.feature_cart_screen.data.network.CartApi
import com.solodilov.feature_cart_screen.data.repository.CartRepositoryImpl
import com.solodilov.feature_cart_screen.domain.repository.CartRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
interface DataModule {

    @Binds
    @FeatureScope
    fun bindCartDataSource(impl: CartDataSourceImpl): CartDataSource

    @Binds
    @FeatureScope
    fun bindCartRepository(impl: CartRepositoryImpl): CartRepository

    companion object {
        @Provides
        @FeatureScope
        fun provideCartApi(retrofit: Retrofit) : CartApi =
            retrofit.create(CartApi::class.java)
    }

}