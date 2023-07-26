package com.idat.ec3.repository

import com.idat.ec3.response.ApiResponse
import com.idat.ec3.response.PunkApiResponse
import com.idat.ec3.retrofit.RetrofitInstance

class PunkApiRepository {

    suspend fun getPunk(): ApiResponse<PunkApiResponse> {
        return try {
            val response = RetrofitInstance.getPunkApiService().getPunkApis()
            ApiResponse.Success(response)
        }catch (e:Exception){
            ApiResponse.Error(e)
        }


    }
}