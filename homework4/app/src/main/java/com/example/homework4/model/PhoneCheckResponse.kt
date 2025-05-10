package com.example.homework4.model

import kotlinx.serialization.Serializable

@Serializable
data class PhoneCheckResponse(
    val is_valid: Boolean,
    val is_formatted_properly: Boolean,
    val country: String = "",
    val location: String = "",
    val format_national: String = "",
    val format_international: String = "",
    val format_e164: String = "",
    val country_code: Int = 0,
    val timezones: List<String> = emptyList()
)
