package com.example.homework1.data

import kotlin.random.Random

class OTPgen {
    fun generateOtp(): String {
        return (100000..999999).random().toString()
    }
}
