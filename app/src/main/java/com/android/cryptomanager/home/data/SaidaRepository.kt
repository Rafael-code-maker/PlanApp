package com.android.cryptomanager.home.data

import com.google.firebase.auth.FirebaseAuth

class SaidaRepository {
    class SaidaRepository {
        suspend fun getEntradas() {
            val userId = FirebaseAuth.getInstance().currentUser?.uid
            if (userId!=null){
            }
        }
    }
}