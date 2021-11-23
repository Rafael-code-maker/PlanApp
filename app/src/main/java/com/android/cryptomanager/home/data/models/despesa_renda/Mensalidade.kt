package com.android.cryptomanager.home.data.models.despesa_renda

import java.io.Serializable

data class Mensalidade(
    var descricao : String,
    var valor : Double?,
    var data : String,
    var periodo : String
) : Serializable