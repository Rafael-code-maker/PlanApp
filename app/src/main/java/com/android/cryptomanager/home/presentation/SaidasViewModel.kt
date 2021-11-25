package com.android.cryptomanager.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.cryptomanager.home.data.SaidaRepository
import com.android.cryptomanager.home.data.models.Expenditure
import com.android.cryptomanager.home.data.models.Income
import kotlinx.coroutines.launch

class SaidasViewModel(private val saidaRepository: SaidaRepository) : ViewModel() {

    fun saveExpenditure(income : Expenditure){
        viewModelScope.launch {
            saidaRepository.addSaida(income)
        }
    }

}