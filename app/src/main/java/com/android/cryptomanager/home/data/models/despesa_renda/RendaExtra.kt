package com.android.cryptomanager.home.data.models.despesa_renda

import java.io.Serializable

data class RendaExtra(
    var descricao : String,
    var valor : Double?
) : Serializable