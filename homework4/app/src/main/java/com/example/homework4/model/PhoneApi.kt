package com.example.homework4.model

import retrofit2.http.GET
import retrofit2.http.Query

interface PhoneApi {
    @GET("v1/validatephone")
    suspend fun validatePhone(@Query("number") phoneNumber: String): PhoneCheckResponse
}