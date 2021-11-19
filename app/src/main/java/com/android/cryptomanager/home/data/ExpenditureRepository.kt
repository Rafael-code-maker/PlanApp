package com.android.cryptomanager.home.data

import com.android.cryptomanager.home.data.models.Expenditure
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.tasks.await

class ExpenditureRepository {
    suspend fun getExpenditures(): List<Expenditure>? {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId!=null){
            val databaseReference =
                FirebaseDatabase.getInstance().getReference("users").child(userId)
            return databaseReference.child("expenditures")
                .get().await().getValue<List<Expenditure>>()
        }
        return listOf()
    }
}