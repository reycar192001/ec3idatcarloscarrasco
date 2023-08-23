package com.idat.ec3.repository

import com.idat.ec3.PunkApi
import com.idat.ec3.db.PunkApiDao
import com.idat.ec3.response.ApiResponse
import com.idat.ec3.response.PunkApiResponse
import com.idat.ec3.retrofit.RetrofitInstance

class PunkApiRepository(val punkApiDao: PunkApiDao?=null) {

    suspend fun getPunk(): ApiResponse<List<PunkApi>> {
        return try {
            val response = RetrofitInstance.punkApiService.getPunkApis()
            ApiResponse.Success(response)
        } catch (e: Exception) {
            ApiResponse.Error(e)
        }
    }

    suspend fun addFavorite(punkApi: PunkApi){
        punkApiDao?.let{
            it.addFavorite(punkApi)

        }
    }
    fun getFavorites(): List<PunkApi>{
        punkApiDao?.let {
            return  it.getFavorites()
        }?: kotlin.run {
            return listOf()
        }
    }

}
