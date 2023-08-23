package com.idat.ec3.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.idat.ec3.PunkApi
import com.idat.ec3.db.PunkApiDataBase
import com.idat.ec3.repository.PunkApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritoViewModel(application: Application): AndroidViewModel(application) {
    private val repository: PunkApiRepository
    private val _favorites: MutableLiveData<List<PunkApi>> = MutableLiveData()

    val favorites: LiveData<List<PunkApi>> = _favorites

    init {
        val db = PunkApiDataBase.getDatabase(application)
        repository = PunkApiRepository(db.punkApiDao())
        fetchFavorites()
    }

    fun fetchFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getFavorites()
            withContext(Dispatchers.Main) {
                _favorites.value = data
            }
        }
    }

    // Esta función debe ser pública para poder acceder desde el fragmento
    fun getFavorites() {
        fetchFavorites()
    }


}

