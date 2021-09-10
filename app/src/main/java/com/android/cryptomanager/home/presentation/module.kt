package com.android.cryptomanager.home.presentation

import com.android.cryptomanager.home.data.api.ApiInterface
import com.android.cryptomanager.home.data.models.CryptoCard
import com.android.cryptomanager.home.data.repositories.HomeRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    viewModel { (cryptoCard: CryptoCard) ->
        AddViewModel(cryptoCard, get())
    }

    factory {
        ApiInterface.create()
    }

    factory {
        HomeRepository(get())
    }

}
