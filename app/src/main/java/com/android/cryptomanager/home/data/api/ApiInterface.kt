package com.android.cryptomanager.home.data.api

import com.android.cryptomanager.home.data.models.BitcoinResponse
import com.android.cryptomanager.home.data.models.ChilizResponse
import com.android.cryptomanager.home.data.models.EthereumResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("BTC/ticker/")
    suspend fun bitcoin(): Response<BitcoinResponse>

    @GET("CHZ/ticker/")
    suspend fun chiliz(): Response<ChilizResponse>

    @GET("ETH/ticker/")
    suspend fun ethereum(): Response<EthereumResponse>

    companion object {
        private const val BASE_URL =
            "https://www.mercadobitcoin.net/api/"

        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}
