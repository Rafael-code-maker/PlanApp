package com.android.cryptomanager.home.presentation

import com.android.cryptomanager.home.data.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel {
        ExpendituresViewModel(get())
    }
    factory {
        ExpendituresRepository()
    }
    viewModel {
        ExpenditureViewModel(get())
    }
    factory {
        ExpenditureRepository()
    }
    viewModel {
        IncomeListViewModel(get())
    }
    viewModel {
        EntradasViewModel(get())
    }
    viewModel {
        SaidasViewModel(get())
    }
    factory {
        IncomeListRepository()
    }
    viewModel {
        IncomeViewModel(get())
    }
    factory {
        EntradaRepository()
    }
    factory {
        SaidaRepository()
    }
}
