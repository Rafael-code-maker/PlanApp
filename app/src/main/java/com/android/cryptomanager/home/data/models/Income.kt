package com.android.cryptomanager.home.data.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Income(
    val name: String?=null,
    val value: String?=null,
    val date: String?=null,
    val description: String?=null
)
