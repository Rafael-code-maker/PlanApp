package com.android.cryptomanager.home.data

import android.util.Log
import com.android.cryptomanager.home.data.models.Expenditure
import com.android.cryptomanager.home.data.models.Income
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.tasks.await

class EntradaRepository {

    suspend fun addEntrada(income: Income) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val databaseReference =
                FirebaseDatabase.getInstance().getReference("users").child(userId)
            val incomeList = databaseReference.child("incomes")
                .get().await().getValue<List<Income>>()
            if (incomeList != null) {
                val newList = incomeList.toMutableList()
                newList.add(income)
                databaseReference.child("incomes").setValue(newList).await()
            } else {
                databaseReference.child("incomes").setValue(listOf(income)).await()
            }
        } else {
            throw Exception("Erro Desconhecido")
        }
    }

    suspend fun somaEntradas(): Double {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        var sum = 0.0
        if (userId!=null){
            val databaseReference =
                FirebaseDatabase.getInstance().getReference("users").child(userId)
            val lista = databaseReference.child("incomes")
                .get().await().getValue<List<Income>>()

            if (lista != null) {
                lista.forEach {
                    sum += it.value?.toDouble()!!
                }
            }
        }
        Log.d("Soma Entrada",sum.toString())
        return sum
    }

    suspend fun somaSaidas(): Double {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        var sum = 0.0
        if (userId!=null){
            val databaseReference =
                FirebaseDatabase.getInstance().getReference("users").child(userId)
            val lista = databaseReference.child("expenditures")
                .get().await().getValue<List<Expenditure>>()

            lista?.forEach {
                sum += it.price?.toDouble()!!
            }
        }
        Log.d("Soma Saída",sum.toString())
        return sum
    }

}

