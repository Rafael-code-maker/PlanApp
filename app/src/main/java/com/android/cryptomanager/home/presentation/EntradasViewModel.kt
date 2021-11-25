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
    private val _somaEntrada = MutableLiveData<Double>()
    val somaEntrada: LiveData<Double> = _somaEntrada
    private val _somaSaida = MutableLiveData<Double>()
    val somaSaida: LiveData<Double> = _somaSaida

    init {
        _loading.postValue(true)
        viewModelScope.launch {
            somaEntradas()
            somaSaidas()
            _loading.postValue(false)
        }
    }

    fun saveEntrie(income: Income) {
        _loading.postValue(true)
        viewModelScope.launch {
            entradaRepository.addEntrada(income)
            _insertionFinished.postValue(Unit)
            _loading.postValue(false)
        }
    }

    suspend fun somaEntradas() {
        var sum = 0.0
        sum = entradaRepository.somaEntradas()
        _somaEntrada.postValue(sum)
    }

    suspend fun somaSaidas() {
        var sum = 0.0
        sum = entradaRepository.somaSaidas()
        _somaSaida.postValue(sum)
    }
}