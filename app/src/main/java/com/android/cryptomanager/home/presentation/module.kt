package com.android.cryptomanager.home.presentation

import com.android.cryptomanager.home.data.ExpenditureRepository
import com.android.cryptomanager.home.data.ExpendituresRepository
import com.android.cryptomanager.home.data.IncomeListRepository
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
    factory {
        IncomeListRepository()
    }
    viewModel {
        IncomeViewModel(get())
    }
}
