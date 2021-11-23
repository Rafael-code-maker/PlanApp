package com.android.cryptomanager.home.data.models

data class Expenditure(
    val name: String,
    val price: String,
    val date: String,
    val description: String,
    val numberOfParcels: String = "1"
)
