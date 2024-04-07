package com.caiiiyua.kredkarts.data.network

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface KredKartsNetworkService {
    suspend fun getCreditCards(page: Int = 1): List<CreditCardDto>
}

class RetrofitKredKartsNetwork(
    json: Json,
    okhttpCallFactory: dagger.Lazy<okhttp3.Call.Factory>,
) : KredKartsNetworkService {

    private val networkApi =
        Retrofit.Builder()
            .baseUrl(KREDKARTS_BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .callFactory(okhttpCallFactory.get())
            .build()
            .create(RetrofitKredKartsNetworkApi::class.java)


    companion object {
        private const val KREDKARTS_BASE_URL = "https://random-data-api.com/api/v2/"
    }

    override suspend fun getCreditCards(page: Int): List<CreditCardDto> {
       return networkApi.getCreditCards(page = page)
    }
}

private interface RetrofitKredKartsNetworkApi {
    @GET(value = "credit_cards")
    suspend fun getCreditCards(
        @Query("size") size: Int = 20,
        @Query("page") page: Int,
    ): List<CreditCardDto>
}