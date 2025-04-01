package com.example.homework1.data

class OTPgen {
    fun generateOtp(): String {
        return (100000..999999).random().toString()
    }
}
