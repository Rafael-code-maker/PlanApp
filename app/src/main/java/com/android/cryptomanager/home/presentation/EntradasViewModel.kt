package com.android.cryptomanager.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.cryptomanager.home.data.EntradaRepository
import com.android.cryptomanager.home.data.models.Income
import kotlinx.coroutines.launch

class EntradasViewModel(private val entradaRepository: EntradaRepository) : ViewModel() {

    fun saveEntrie(income : Income){
        viewModelScope.launch {
            entradaRepository.addEntrada(income)
        }
    }

    fun somaEntradas(): Double {
        var sum = 0.0
        viewModelScope.launch {
            sum =  entradaRepository.somaEntradas()
        }
        return sum
    }

    fun somaSaidas(): Double {
        var sum = 0.0
        viewModelScope.launch {
           sum =  entradaRepository.somaSaidas()
        }
        return sum
    }
}