package com.caiiiyua.kredkarts.di

import androidx.compose.ui.util.trace
import com.caiiiyua.kredkarts.data.CreditCardsRepository
import com.caiiiyua.kredkarts.data.CreditCardsRepositoryImpl
import com.caiiiyua.kredkarts.data.local.CreditCardsDataSource
import com.caiiiyua.kredkarts.data.local.CreditCardsLocalDataSource
import com.caiiiyua.kredkarts.data.network.KredKartsNetworkService
import com.caiiiyua.kredkarts.data.network.RetrofitKredKartsNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataModules {

    @Provides
    @Singleton
    fun providesJsonResponse(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun providesOkHttpFactory(): Call.Factory = trace("KredKartsOkHttpClient") {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    },
            )
            .build()
    }

    @Provides
    @Singleton
    fun providesKredKartsNetworkService(
        json: Json,
        callFactory: dagger.Lazy<Call.Factory>,
    ): KredKartsNetworkService = RetrofitKredKartsNetwork(
        json = json,
        okhttpCallFactory = callFactory,
    )

    @Provides
    @Singleton
    fun providesCreditCardsDataSource(): CreditCardsDataSource = CreditCardsLocalDataSource()

    @Provides
    @Singleton
    fun providesCreditCardsRepository(
        creditCardsDataSource: CreditCardsDataSource,
        kredKartsNetworkService: KredKartsNetworkService,
    ): CreditCardsRepository = CreditCardsRepositoryImpl(creditCardsDataSource, kredKartsNetworkService)
}