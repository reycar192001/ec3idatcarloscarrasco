package com.idat.ec3.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idat.ec3.PunkApi
import com.idat.ec3.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PunkapiListViewModel : ViewModel() {
    //val repository= PunkApiRepository()
    //val punkapiList: MutableLiveData<List<PunkApi>> = MutableLiveData<List<PunkApi>>()

    private val punkApiService = RetrofitInstance.punkApiService
    val punkApiResponse: MutableLiveData<List<PunkApi>> = MutableLiveData()



    /*fun getPunkApiList() {
        val data = getData()
        punkapiList.value = data
    }*/

    fun getCouponsFromService() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = punkApiService.getPunkApis()
                punkApiResponse.postValue(response)
            } catch (e: Exception) {
                // Manejar el error
            }
        }
    }
}