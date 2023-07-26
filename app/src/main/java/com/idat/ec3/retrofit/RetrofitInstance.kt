package com.idat.ec3.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit = Retrofit.Builder()

        .baseUrl("https://api.punkapi.com/v2/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun getPunkApiService() : PunkApiService = retrofit.create(PunkApiService::class.java)

}