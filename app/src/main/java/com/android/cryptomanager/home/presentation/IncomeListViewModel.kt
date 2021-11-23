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

class IncomeListViewModel(
    private val incomeRepository: IncomeListRepository
) : ViewModel() {
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _incomesList = MutableLiveData<List<Income>?>()
    val incomesList: LiveData<List<Income>?> = _incomesList

    fun getIncomes() {
        _loading.postValue(true)
        viewModelScope.launch {
            try{
                val newIncomeList = incomeRepository.getIncomes()
                if(newIncomeList!=null)
                    _incomesList.postValue(newIncomeList)
            }catch (e: Exception) {
                Log.e("error", e.toString())
            }
            _loading.postValue(false)
        }
    }
}