package com.android.cryptomanager.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.cryptomanager.home.data.models.BitcoinUserData
import com.android.cryptomanager.home.data.models.ChilizUserData
import com.android.cryptomanager.home.data.models.CryptoCard
import com.android.cryptomanager.home.data.models.EthereumUserData
import com.android.cryptomanager.home.data.repositories.HomeRepository
import kotlinx.coroutines.launch

class AddViewModel(private val cryptoCard: CryptoCard, private val homeRepository: HomeRepository) :
    ViewModel() {

    private val _currentValueLive = MutableLiveData<Double>()
    val currentValueLive: LiveData<Double> = _currentValueLive

    private val _currentQuantieLive = MutableLiveData<Double>()
    val currentQuantieLive: LiveData<Double> = _currentQuantieLive

    private val _mediumPrice = MutableLiveData<String>()
    val mediumPrice: LiveData<String> = _mediumPrice

    private val _parcialPrice = MutableLiveData<String>()
    val parcialPrice: LiveData<String> = _parcialPrice

    private val _totalPrice = MutableLiveData<String>()
    val totalPrice: LiveData<String> = _totalPrice

    private val _coinPrice = MutableLiveData<String>()
    val coinPrice: LiveData<String> = _coinPrice

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private var currentValue: Double = 0.0
    private var currentQuantie: Double = 0.0

    init {
        _loading.postValue(true)
        viewModelScope.launch {
            when (cryptoCard.coinTitle) {
                "Bitcoin" -> {
                    updateBitcoinPrice()
                }
                "Ethereum" -> {
                    updateEthereumPrice()
                }
                "Chiliz" -> {
                    updateChilizPrice()
                }
            }
            updateCurrentValues()
            _loading.postValue(false)
        }
    }

    suspend fun updateBitcoinPrice() {
        _coinPrice.postValue(homeRepository.getBitcoinPrice().bitcoin.last)
    }

    suspend fun updateEthereumPrice() {
        _coinPrice.postValue(homeRepository.getEthereumPrice().ethereum.last)
    }

    suspend fun updateChilizPrice() {
        _coinPrice.postValue(homeRepository.getChilizPrice().chiliz.last)
    }

    fun updateCurrentValues() {
        viewModelScope.launch {
            when (cryptoCard.coinTitle) {
                "Bitcoin" -> {
                    if (homeRepository.getBitcoin()?.value?.toDoubleOrNull() != null) {
                        currentValue = homeRepository.getBitcoin()?.value?.toDoubleOrNull()!!
                    } else {
                        currentValue = 0.0
                    }
                    if (homeRepository.getBitcoin()?.quantitie?.toDoubleOrNull() != null) {
                        currentQuantie = homeRepository.getBitcoin()?.quantitie?.toDoubleOrNull()!!
                    } else {
                        currentQuantie = 0.0
                    }
                    _currentValueLive.postValue(currentValue)
                    _currentQuantieLive.postValue(currentQuantie)
                }
                "Ethereum" -> {
                    if (homeRepository.getEthereum()?.value?.toDoubleOrNull() != null) {
                        currentValue = homeRepository.getEthereum()?.value?.toDoubleOrNull()!!
                    } else {
                        currentValue = 0.0
                    }
                    if (homeRepository.getEthereum()?.quantitie?.toDoubleOrNull() != null) {
                        currentQuantie = homeRepository.getEthereum()?.quantitie?.toDoubleOrNull()!!
                    } else {
                        currentQuantie = 0.0
                    }
                    _currentValueLive.postValue(currentValue)
                    _currentQuantieLive.postValue(currentQuantie)
                }
                "Chiliz" -> {
                    if (homeRepository.getChiliz()?.value?.toDoubleOrNull() != null) {
                        currentValue = homeRepository.getChiliz()?.value?.toDoubleOrNull()!!
                    } else {
                        currentValue = 0.0
                    }
                    if (homeRepository.getChiliz()?.quantitie?.toDoubleOrNull() != null) {
                        currentQuantie = homeRepository.getChiliz()?.quantitie?.toDoubleOrNull()!!
                    } else {
                        currentQuantie = 0.0
                    }
                    _currentValueLive.postValue(currentValue)
                    _currentQuantieLive.postValue(currentQuantie)
                }
            }
        }
    }

    fun insertNewValue(newValue: String, newQuantie: String) {
        viewModelScope.launch {
            currentValue += newValue.toDouble()
            currentQuantie += newQuantie.toDouble()
            _currentValueLive.postValue(currentValue)
            _currentQuantieLive.postValue(currentQuantie)
            when (cryptoCard.coinTitle) {
                "Bitcoin" -> {
                    homeRepository.saveBitcoin(BitcoinUserData(currentValue.toString(),
                        currentQuantie.toString()))
                }
                "Ethereum" -> {
                    homeRepository.saveEthereum(EthereumUserData(currentValue.toString(),
                        currentQuantie.toString()))
                }
                "Chiliz" -> {
                    homeRepository.saveChiliz(ChilizUserData(currentValue.toString(),
                        currentQuantie.toString()))
                }
            }
        }
    }

    fun removeValue(valueToBeRemoved: String, quantieToBeRemoved: String) {
        viewModelScope.launch {
            if (valueToBeRemoved.toDouble() <= currentValue && quantieToBeRemoved.toDouble() <= currentQuantie) {
                currentValue = currentValue.minus(valueToBeRemoved.toDouble())
                currentQuantie = currentQuantie.minus(quantieToBeRemoved.toDouble())
                _currentValueLive.postValue(currentValue)
                _currentQuantieLive.postValue(currentQuantie)
                when (cryptoCard.coinTitle) {
                    "Bitcoin" -> {
                        homeRepository.saveBitcoin(BitcoinUserData(currentValue.toString(),
                            currentQuantie.toString()))
                    }
                    "Ethereum" -> {
                        homeRepository.saveEthereum(EthereumUserData(currentValue.toString(),
                            currentQuantie.toString()))
                    }
                    "Chiliz" -> {
                        homeRepository.saveChiliz(ChilizUserData(currentValue.toString(),
                            currentQuantie.toString()))
                    }
                }
            }
        }
    }

    fun calculaPrecoMedio() {
        if (currentQuantie > 0 && currentValue > 0) {
            _mediumPrice.postValue((currentValue / currentQuantie).toString())
        } else {
            _mediumPrice.postValue("0.0")
        }
    }

    fun calculoParcial() {
        viewModelScope.launch {
            when (cryptoCard.coinTitle) {
                "Bitcoin" -> {
                    val priceActual =
                        currentQuantie * homeRepository.getBitcoinPrice().bitcoin.last.toDouble()
                    _parcialPrice.postValue((priceActual - (priceActual * 0.07)).toString())
                }
                "Ethereum" -> {
                    val priceActual =
                        currentQuantie * homeRepository.getEthereumPrice().ethereum.last.toDouble()
                    _parcialPrice.postValue((priceActual - (priceActual * 0.07)).toString())
                }
                "Chiliz" -> {
                    val priceActual =
                        currentQuantie * homeRepository.getChilizPrice().chiliz.last.toDouble()
                    _parcialPrice.postValue((priceActual - (priceActual * 0.07)).toString())
                }
            }
        }
    }

    fun calculoTotal() {
        viewModelScope.launch {
            when (cryptoCard.coinTitle) {
                "Bitcoin" -> {
                    val priceActual =
                        currentQuantie * homeRepository.getBitcoinPrice().bitcoin.last.toDouble()
                    _totalPrice.postValue((priceActual - (priceActual * 0.07) - (2.90 + priceActual * 0.0199)).toString())
                }
                "Ethereum" -> {
                    val priceActual =
                        currentQuantie * homeRepository.getEthereumPrice().ethereum.last.toDouble()
                    _totalPrice.postValue((priceActual - (priceActual * 0.07) - (2.90 + priceActual * 0.0199)).toString())
                }
                "Chiliz" -> {
                    val priceActual =
                        currentQuantie * homeRepository.getChilizPrice().chiliz.last.toDouble()
                    _totalPrice.postValue((priceActual - (priceActual * 0.07) - (2.90 + priceActual * 0.0199)).toString())
                }
            }
        }
    }
}
