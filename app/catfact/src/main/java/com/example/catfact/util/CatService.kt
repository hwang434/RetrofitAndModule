package com.example.catfact.util

import com.example.catfact.Cat
import retrofit2.Call
import retrofit2.http.GET

interface CatService {
    @GET("/fact/")
    fun getCatInfo(): Call<Cat>
}