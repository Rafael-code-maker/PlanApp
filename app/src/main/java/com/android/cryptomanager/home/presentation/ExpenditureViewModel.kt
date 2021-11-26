package com.android.cryptomanager.home.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.cryptomanager.home.data.ExpenditureRepository
import com.android.cryptomanager.home.data.ExpendituresRepository
import com.android.cryptomanager.home.data.models.Expenditure
import kotlinx.coroutines.launch
import java.lang.Exception

class ExpenditureViewModel (
    private val expenditureRepository: ExpenditureRepository
) : ViewModel() {
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _expenditure = MutableLiveData<Expenditure>()
    val expenditure: LiveData<Expenditure> = _expenditure

    fun getExpenditures(position : Int) {
        _loading.postValue(true)
        viewModelScope.launch {
            try{
                val newExpenditureList = expenditureRepository.getExpenditures()
                _expenditure.postValue(newExpenditureList?.get(position))
            }catch (e: Exception) {
                Log.e("error", e.toString())
            }
            _loading.postValue(false)
        }
    }
}