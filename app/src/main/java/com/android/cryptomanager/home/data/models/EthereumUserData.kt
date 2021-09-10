package com.android.cryptomanager.home.data.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class EthereumUserData(
    var value: String? = null,
    var quantitie: String? = null,
)