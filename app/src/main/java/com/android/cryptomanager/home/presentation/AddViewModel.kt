package com.android.cryptomanager.home.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.cryptomanager.home.data.models.BitcoinResponse
import com.android.cryptomanager.home.data.repositories.HomeRepository
import kotlinx.coroutines.launch

class AddViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val _currentValueLive = MutableLiveData<Double>()
    val currentValueLive: LiveData<Double> = _currentValueLive

    private val _currentQuantieLive = MutableLiveData<Double>()
    val currentQuantieLive: LiveData<Double> = _currentQuantieLive

    private val _bitcoinPrice = MutableLiveData<String>()
    val bitcoinPrice: LiveData<String> = _bitcoinPrice

    private var currentValue: Double = 0.0
    private var currentQuantie: Double = 0.0
    private lateinit var bitcoin: BitcoinResponse

    init {
        updateBitcoinPrice()
    }

    fun updateBitcoinPrice() {
        viewModelScope.launch {
            bitcoin = homeRepository.getBitcoinPrice()
            Log.i("Price", bitcoin.bitcoin.last)
            _bitcoinPrice.postValue(bitcoin.bitcoin.last)
        }
    }

    fun insertNewValue(newValue: String, newQuantie: String) {
        currentValue += newValue.toDouble()
        currentQuantie += newQuantie.toDouble()
        _currentValueLive.postValue(currentValue)
        _currentQuantieLive.postValue(currentQuantie)
    }

    fun removeValue(valueToBeRemoved: String, quantieToBeRemoved: String) {
        if (valueToBeRemoved.toDouble() < currentValue && quantieToBeRemoved.toDouble() < currentQuantie) {
            currentValue = currentValue.minus(valueToBeRemoved.toDouble())
            currentQuantie = currentQuantie.minus(quantieToBeRemoved.toDouble())
            _currentValueLive.postValue(currentValue)
            _currentQuantieLive.postValue(currentQuantie)
        }
    }

    fun calculoParcial() {

    }

    fun calculoTotal() {

    }

}