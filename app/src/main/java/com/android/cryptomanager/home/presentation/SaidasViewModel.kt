package com.android.cryptomanager.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.cryptomanager.home.data.SaidaRepository
import com.android.cryptomanager.home.data.models.Expenditure
import com.android.cryptomanager.home.data.models.Income
import kotlinx.coroutines.launch

class SaidasViewModel(private val saidaRepository: SaidaRepository) : ViewModel() {
    private val _insertionFinished = MutableLiveData<Unit>()
    val insertionFinished: LiveData<Unit> = _insertionFinished
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun saveExpenditure(income : Expenditure){
        _loading.postValue(true)
        viewModelScope.launch {
            saidaRepository.addSaida(income)
            _insertionFinished.postValue(Unit)
            _loading.postValue(false)
        }
    }

}