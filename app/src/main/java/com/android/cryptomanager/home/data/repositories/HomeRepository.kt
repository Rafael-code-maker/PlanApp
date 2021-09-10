package com.android.cryptomanager.home.data.repositories

import android.util.Log
import com.android.cryptomanager.home.data.api.ApiInterface
import com.android.cryptomanager.home.data.models.BitcoinResponse
import com.android.cryptomanager.home.data.models.Chiliz
import com.android.cryptomanager.home.data.models.Ethereum
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository(private val apiInterface: ApiInterface) {

    suspend fun getBitcoinPrice(): BitcoinResponse {
        return withContext(Dispatchers.IO) {
            val response = apiInterface.bitcoin()

            Log.i("Api code", response.code().toString())
            Log.i("Api body", response.body().toString())
            return@withContext response.body()!!

        }
    }

    suspend fun getEthereumPrice(): Ethereum {
        return withContext(Dispatchers.IO) {
            val response = apiInterface.ethereum()

            return@withContext response.body()!!

        }
    }

    suspend fun getChilizPrice(): Chiliz {
        return withContext(Dispatchers.IO) {
            val response = apiInterface.chiliz()

            return@withContext response.body()!!

        }
    }

    companion object {
        private const val NEW_RESPONSE = 200
    }
}
