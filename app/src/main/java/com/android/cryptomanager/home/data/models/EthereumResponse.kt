package com.android.cryptomanager.home.data.models

import com.google.gson.annotations.SerializedName

data class EthereumResponse(
    @SerializedName("ticker") val ethereum: Ethereum,
)
