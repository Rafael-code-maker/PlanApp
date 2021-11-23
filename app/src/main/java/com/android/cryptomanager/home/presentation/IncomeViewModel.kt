package com.android.cryptomanager.home.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.cryptomanager.home.data.IncomeListRepository
import com.android.cryptomanager.home.data.models.Expenditure
import com.android.cryptomanager.home.data.models.Income
import kotlinx.coroutines.launch
import java.lang.Exception

class IncomeViewModel(
    private val incomeListRepository: IncomeListRepository
): ViewModel() {
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _income = MutableLiveData<Income>()
    val income: LiveData<Income> = _income

    fun getIncome(position : Int) {
        _loading.postValue(true)
        viewModelScope.launch {
            try{
                val newExpenditureList = incomeListRepository.getIncomes()
                _income.postValue(newExpenditureList?.get(position))
            }catch (e: Exception) {
                Log.e("error", e.toString())
            }
            _loading.postValue(false)
        }
    }
}