package com.android.cryptomanager.home.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.cryptomanager.home.data.ExpendituresRepository
import com.android.cryptomanager.home.data.models.Expenditure
import kotlinx.coroutines.launch
import java.lang.Exception

class ExpendituresViewModel(
    private val expendituresRepository: ExpendituresRepository
) : ViewModel() {
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _expendituresList = MutableLiveData<List<Expenditure>?>()
    val expenditureList: LiveData<List<Expenditure>?> = _expendituresList

    fun getExpenditures() {
        _loading.postValue(true)
        viewModelScope.launch {
            try{
                val newExpenditureList = expendituresRepository.getExpenditures()
                _expendituresList.postValue(newExpenditureList)
            }catch (e: Exception) {
                Log.e("error", e.toString())
            }
            _loading.postValue(false)
        }
    }
}
