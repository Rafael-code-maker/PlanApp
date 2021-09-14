package com.android.cryptomanager.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.cryptomanager.home.data.repositories.HomeRepository
import kotlinx.coroutines.launch

class OverviewViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val _bitcoinInvested = MutableLiveData<Double>()
    val bitcoinInvested: LiveData<Double> = _bitcoinInvested

    private val _ethereumInvested = MutableLiveData<Double>()
    val ethereumInvested: LiveData<Double> = _ethereumInvested

    private val _chilizInvested = MutableLiveData<Double>()
    val chilizInvested: LiveData<Double> = _chilizInvested

    private val _totalInvested = MutableLiveData<Double>()
    val totalInvested: LiveData<Double> = _totalInvested

    private val _totalInvestedActualCotation = MutableLiveData<Double>()
    val totalInvestedActualCotation: LiveData<Double> = _totalInvestedActualCotation

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private var investedTotal = 0.0
    private var investedTotalonActualCotation = 0.0

    init {
        _loading.postValue(true)
        viewModelScope.launch {
            updateCurrentValues()
            totalInvestedOnActualCotation()
            _loading.postValue(false)
        }
    }

    suspend fun updateCurrentValues() {

        if (homeRepository.getBitcoin()?.value?.toDoubleOrNull() != null) {
            _bitcoinInvested.postValue(homeRepository.getBitcoin()?.value?.toDoubleOrNull()!!)
            investedTotal += homeRepository.getBitcoin()?.value?.toDoubleOrNull()!!
        } else {
            _bitcoinInvested.postValue("0.0".toDoubleOrNull())
            investedTotal += 0.0
        }

        if (homeRepository.getEthereum()?.value?.toDoubleOrNull() != null) {
            _ethereumInvested.postValue(homeRepository.getEthereum()?.value?.toDoubleOrNull()!!)
            investedTotal += homeRepository.getEthereum()?.value?.toDoubleOrNull()!!
        } else {
            _ethereumInvested.postValue("0.0".toDoubleOrNull())
            investedTotal += 0.0
        }

        if (homeRepository.getChiliz()?.value?.toDoubleOrNull() != null) {
            _chilizInvested.postValue(homeRepository.getChiliz()?.value?.toDoubleOrNull()!!)
            investedTotal += homeRepository.getChiliz()?.value?.toDoubleOrNull()!!
        } else {
            _chilizInvested.postValue("0.0".toDoubleOrNull())
            investedTotal += 0.0
        }

        _totalInvested.postValue(investedTotal)
    }

    suspend fun totalInvestedOnActualCotation() {

        if (homeRepository.getBitcoin()?.quantitie?.toDoubleOrNull() != null) {
            investedTotalonActualCotation += homeRepository.getBitcoin()?.quantitie?.toDoubleOrNull()!! * homeRepository.getBitcoinPrice().bitcoin.last.toDoubleOrNull()!!
        } else {
            investedTotalonActualCotation += 0.0
        }

        if (homeRepository.getEthereum()?.quantitie?.toDoubleOrNull() != null) {
            investedTotalonActualCotation += homeRepository.getEthereum()?.quantitie?.toDoubleOrNull()!! * homeRepository.getEthereumPrice().ethereum.last.toDoubleOrNull()!!
        } else {
            investedTotalonActualCotation += 0.0
        }

        if (homeRepository.getChiliz()?.quantitie?.toDoubleOrNull() != null) {
            investedTotalonActualCotation += homeRepository.getChiliz()?.quantitie?.toDoubleOrNull()!! * homeRepository.getChilizPrice().chiliz.last.toDoubleOrNull()!!
        } else {
            investedTotalonActualCotation += 0.0
        }

        _totalInvestedActualCotation.postValue(investedTotalonActualCotation)
    }

}