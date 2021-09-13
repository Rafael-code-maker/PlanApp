package com.android.cryptomanager.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.cryptomanager.home.data.repositories.HomeRepository
import kotlinx.coroutines.launch

class InvestimentosViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val _bitcoinPrice = MutableLiveData<Double>()
    val bitcoinPrice: LiveData<Double> = _bitcoinPrice

    private val _ethereumPrice = MutableLiveData<Double>()
    val ethereumPrice: LiveData<Double> = _ethereumPrice

    private val _chilizPrice = MutableLiveData<Double>()
    val chilizPrice: LiveData<Double> = _chilizPrice

    private val _bitcoinInvested = MutableLiveData<Double>()
    val bitcoinInvested: LiveData<Double> = _bitcoinInvested

    private val _ethereumInvested = MutableLiveData<Double>()
    val ethereumInvested: LiveData<Double> = _ethereumInvested

    private val _chilizInvested = MutableLiveData<Double>()
    val chilizInvested: LiveData<Double> = _chilizInvested

    private val _totalInvested = MutableLiveData<Double>()
    val totalInvested: LiveData<Double> = _totalInvested

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    init {
        _loading.postValue(false)
        viewModelScope.launch {
            updateBitcoinPrice()
            updateEthereumPrice()
            updateChilizPrice()
            updateCurrentValues()
            _loading.postValue(true)
        }
    }

    suspend fun updateBitcoinPrice() {
        _bitcoinPrice.postValue(homeRepository.getBitcoinPrice().bitcoin.last.toDoubleOrNull())
        updateCurrentValues()
    }

    private suspend fun updateEthereumPrice() {
        _ethereumPrice.postValue(homeRepository.getEthereumPrice().ethereum.last.toDoubleOrNull())
        updateCurrentValues()
    }

    suspend fun updateChilizPrice() {
        _chilizPrice.postValue(homeRepository.getChilizPrice().chiliz.last.toDoubleOrNull())
        updateCurrentValues()
    }

    suspend fun updateCurrentValues() {
        var totalInvested: Double = 0.0

        if (homeRepository.getBitcoin()?.value?.toDoubleOrNull() != null) {
            _bitcoinInvested.postValue(homeRepository.getBitcoin()?.value?.toDoubleOrNull()!!)
            totalInvested += homeRepository.getBitcoin()?.value?.toDoubleOrNull()!!
        } else {
            _bitcoinInvested.postValue("0.0".toDoubleOrNull())
            totalInvested += 0.0
        }

        if (homeRepository.getEthereum()?.value?.toDoubleOrNull() != null) {
            _ethereumInvested.postValue(homeRepository.getEthereum()?.value?.toDoubleOrNull()!!)
            totalInvested += homeRepository.getEthereum()?.value?.toDoubleOrNull()!!
        } else {
            _ethereumInvested.postValue("0.0".toDoubleOrNull())
            totalInvested += 0.0
        }

        if (homeRepository.getChiliz()?.value?.toDoubleOrNull() != null) {
            _chilizInvested.postValue(homeRepository.getChiliz()?.value?.toDoubleOrNull()!!)
            totalInvested += homeRepository.getChiliz()?.value?.toDoubleOrNull()!!
        } else {
            _chilizInvested.postValue("0.0".toDoubleOrNull())
            totalInvested += 0.0
        }

        _totalInvested.postValue(totalInvested)
    }

}