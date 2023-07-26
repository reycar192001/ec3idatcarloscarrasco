package com.idat.ec3

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idat.ec3.repository.PunkApiRepository
import com.idat.ec3.response.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PunkapiListViewModel: ViewModel() {
    val repository = PunkApiRepository()
    val punkapiList: MutableLiveData<List<PunkApi>> = MutableLiveData<List<PunkApi>>()

    fun getPunkApi1List(){
        val data = getData()
        punkapiList.value = data
    }

    fun getCouponsFromService(){
        viewModelScope.launch (Dispatchers.IO){
            val response = repository.getPunk()
            when(response){
                is ApiResponse.Error -> {
                    //colocar error
                }
                is ApiResponse.Success ->{
                    punkapiList.postValue(response.data.elementos)
                }
            }
        }
    }
}