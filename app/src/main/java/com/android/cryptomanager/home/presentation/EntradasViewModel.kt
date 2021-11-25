package com.android.cryptomanager.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.cryptomanager.home.data.EntradaRepository
import com.android.cryptomanager.home.data.models.Income
import kotlinx.coroutines.launch

class EntradasViewModel(private val entradaRepository: EntradaRepository) : ViewModel() {
    private val _insertionFinished = MutableLiveData<Unit>()
    val insertionFinished: LiveData<Unit> = _insertionFinished
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun saveEntrie(income : Income){
        _loading.postValue(true)
        viewModelScope.launch {
            entradaRepository.addEntrada(income)
            _insertionFinished.postValue(Unit)
            _loading.postValue(false)
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