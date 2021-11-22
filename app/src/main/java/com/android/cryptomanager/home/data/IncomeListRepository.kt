package com.android.cryptomanager.home.data

import com.android.cryptomanager.home.data.models.Expenditure
import com.android.cryptomanager.home.data.models.Income
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.tasks.await

class IncomeListRepository {
    suspend fun getIncomes(): List<Income>? {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId!=null){
            val databaseReference =
                FirebaseDatabase.getInstance().getReference("users").child(userId)
            return databaseReference.child("incomes")
                .get().await().getValue<List<Income>>()
        }
        return listOf()
    }
}