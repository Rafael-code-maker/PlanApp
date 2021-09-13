package com.android.cryptomanager.home.data.models

import java.io.Serializable

data class CryptoCard(
    val image: Int,
    val coinTitle: String,
    val coinValue: String,
    val investedValue: String,
) : Serializable
