package com.idat.ec3.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit = Retrofit.Builder()

        .baseUrl("https://api.punkapi.com/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    //fun getPunkApiService() : PunkApiService = retrofit.create(PunkApiService::class.java)
    val punkApiService: PunkApiService = retrofit.create(PunkApiService::class.java)

}