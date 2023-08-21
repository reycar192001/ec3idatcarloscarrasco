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
    val punkapiList: MutableLiveData<List<Beer>> = MutableLiveData()

    fun getPunkApi1List(){
        val data = getData()
        punkapiList.value = data
    }

    fun getCouponsFromService() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getPunk()
                if (response.isNotEmpty()) {
                    punkapiList.postValue(response)
                } else {
                    // Manejar caso de lista vacía si es necesario
                }
            } catch (e: Exception) {
                // Manejar el error aquí
                val errorMessage = e.message
                // Hacer algo con el mensaje de error
            }
        }
    }
}