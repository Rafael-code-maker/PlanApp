package com.android.cryptomanager.home.data.models

import com.google.gson.annotations.SerializedName

data class ChilizResponse(
    @SerializedName("ticker") val chiliz: Chiliz,
)
