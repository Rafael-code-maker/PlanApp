package com.android.cryptomanager.home.presentation

import com.android.cryptomanager.home.data.api.ApiInterface
import com.android.cryptomanager.home.data.repositories.HomeRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    viewModel {
        AddViewModel(get())
    }

    factory {
        ApiInterface.create()
    }

    factory {
        HomeRepository(get())
    }

}
