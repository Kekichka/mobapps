package com.example.homework4.model

class PhoneCheckRepo(private val apiService: PhoneApi) {

    suspend fun validatePhoneNumber(phoneNumber: String): PhoneCheckResponse {
        return apiService.validatePhone(phoneNumber)
    }
}