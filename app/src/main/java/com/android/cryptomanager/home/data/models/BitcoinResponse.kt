package com.android.cryptomanager.home.data.models

import com.google.gson.annotations.SerializedName

data class BitcoinResponse(
    @SerializedName("ticker") val bitcoin: Bitcoin,
)