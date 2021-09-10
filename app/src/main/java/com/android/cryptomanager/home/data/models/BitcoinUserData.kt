package com.android.cryptomanager.home.data.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class BitcoinUserData(
    var value: String? = null,
    var quantitie: String? = null,
)