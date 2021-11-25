package com.android.cryptomanager.home.data

import com.android.cryptomanager.home.data.models.Expenditure
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.tasks.await

class SaidaRepository {

    suspend fun addSaida(expenditure: Expenditure) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val databaseReference =
                FirebaseDatabase.getInstance().getReference("users").child(userId)
            val expenditureList = databaseReference.child("expenditures")
                .get().await().getValue<List<Expenditure>>()
            if (expenditureList != null) {
                val newList = expenditureList.toMutableList()
                newList.add(expenditure)
                databaseReference.child("expenditures").setValue(newList).await()
            } else {
                databaseReference.child("expenditures").setValue(listOf(expenditure)).await()
            }
        } else {
            throw Exception("Erro Desconhecido")
        }
    }

}