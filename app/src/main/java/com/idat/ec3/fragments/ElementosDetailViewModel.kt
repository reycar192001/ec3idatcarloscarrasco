package com.idat.ec3.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.idat.ec3.PunkApi
import com.idat.ec3.db.PunkApiDataBase
import com.idat.ec3.repository.PunkApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ElementosDetailViewModel (application: Application): AndroidViewModel(application){
    private val repository:PunkApiRepository
    init {
        val db =PunkApiDataBase.getDatabase(application)
        repository = PunkApiRepository(db.punkApiDao())
    }

    fun addFavorite(punkApi: PunkApi){
        viewModelScope.launch (Dispatchers.IO){
            repository.addFavorite(punkApi)
        }
    }
}