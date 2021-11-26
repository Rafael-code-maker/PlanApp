package com.android.cryptomanager.home.data.models.despesa_renda

import java.io.Serializable

data class RendaVariavel(
    var descricao : String,
    var valor : Double?,
    var data : String
) : Serializable