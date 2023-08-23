package com.idat.ec3.retrofit

import com.idat.ec3.PunkApi
import com.idat.ec3.response.PunkApiResponse
import retrofit2.http.GET

interface PunkApiService {

    @GET("beers")
    suspend fun getPunkApis() : List<PunkApi>
}