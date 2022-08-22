package com.example.catfact.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CatRetrofit {
    companion object {
        private const val BASE_URL = "https://catfact.ninja"
        val api = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(CatService::class.java)
    }
}