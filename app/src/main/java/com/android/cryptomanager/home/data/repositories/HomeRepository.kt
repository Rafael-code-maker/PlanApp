package com.android.cryptomanager.home.data.repositories

import com.android.cryptomanager.home.data.api.ApiInterface
import com.android.cryptomanager.home.data.models.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class HomeRepository(private val apiInterface: ApiInterface) {

    suspend fun getBitcoinPrice(): BitcoinResponse {
        return withContext(Dispatchers.IO) {
            val response = apiInterface.bitcoin()
            return@withContext response.body()!!
        }
    }

    suspend fun getEthereumPrice(): EthereumResponse {
        return withContext(Dispatchers.IO) {
            val response = apiInterface.ethereum()
            return@withContext response.body()!!
        }
    }

    suspend fun getChilizPrice(): ChilizResponse {
        return withContext(Dispatchers.IO) {
            val response = apiInterface.chiliz()
            return@withContext response.body()!!
        }
    }

    suspend fun getChiliz(): ChilizUserData? {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val databaseReference = FirebaseDatabase.getInstance().getReference(userId)
            return databaseReference.child("chiliz_user_data").get().await()
                .getValue<ChilizUserData>()
        }
        return null
    }

    suspend fun saveChiliz(chilizUserData: ChilizUserData) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val databaseReference = FirebaseDatabase.getInstance().getReference(userId)
            databaseReference.child("chiliz_user_data").setValue(chilizUserData).await()
        } else {
            throw Exception("Erro Desconhecido")
        }
    }

    suspend fun getBitcoin(): BitcoinUserData? {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val databaseReference = FirebaseDatabase.getInstance().getReference(userId)
            return databaseReference.child("bitcoin_user_data").get().await()
                .getValue<BitcoinUserData>()
        }
        return null
    }

    suspend fun saveBitcoin(bitcoinUserData: BitcoinUserData) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val databaseReference = FirebaseDatabase.getInstance().getReference(userId)
            databaseReference.child("bitcoin_user_data").setValue(bitcoinUserData).await()
        } else {
            throw Exception("Erro Desconhecido")
        }
    }

    suspend fun getEthereum(): EthereumUserData? {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val databaseReference = FirebaseDatabase.getInstance().getReference(userId)
            return databaseReference.child("ethereum_user_data").get().await()
                .getValue<EthereumUserData>()
        }
        return null
    }

    suspend fun saveEthereum(ethereumUserData: EthereumUserData) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val databaseReference = FirebaseDatabase.getInstance().getReference(userId)
            databaseReference.child("ethereum_user_data").setValue(ethereumUserData).await()
        } else {
            throw Exception("Erro Desconhecido")
        }
    }

}
