package com.idat.ec3.repository

import com.idat.ec3.Beer
import com.idat.ec3.response.ApiResponse
import com.idat.ec3.response.PunkApiResponse
import com.idat.ec3.retrofit.RetrofitInstance

class PunkApiRepository {

    suspend fun getPunk(): List<Beer> {
        return try {
            val response = RetrofitInstance.getPunkApiService().getPunkApis()
            response // Devuelve directamente la lista de cervezas
        } catch (e: Exception) {
            // Manejar el error y lanzar una excepción personalizada si es necesario
            throw  e
        }
    }
}