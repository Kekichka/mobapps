package com.example.homework4.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    private const val BASE_URL = "https://validate-phone-by-api-ninjas.p.rapidapi.com/"

    fun create(): PhoneApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PhoneApi::class.java)
    }
}