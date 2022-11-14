package com.solodilov.shopapp.di

import com.solodilov.feature_main_screen.data.network.RunMockyApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {

    private companion object {
        const val BASE_URL = "https://run.mocky.io/"
    }

    @Provides
    fun provideBaseUrl(): String =
        BASE_URL

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        baseUrl: String,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .build()

    @Provides
    @Singleton
    fun provideRunMockyApi(
        retrofit: Retrofit,
    ): RunMockyApi =
        retrofit.create(RunMockyApi::class.java)
}
