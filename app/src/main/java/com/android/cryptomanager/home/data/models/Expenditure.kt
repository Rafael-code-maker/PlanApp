package com.android.cryptomanager.home.data.models
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Expenditure(
    val name: String?=null,
    val price: String?=null,
    val date: String?=null,
    val description: String?=null
)
