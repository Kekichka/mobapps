package com.example.homework4.viewModel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework4.model.PhoneCheckRepo
import com.example.homework4.ui.theme.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class PhoneCheckViewModel(private val phoneCheckRepo: PhoneCheckRepo) : ViewModel() {
    var phoneNumber by mutableStateOf("")
    var resultMessage by mutableStateOf("")
    var isError by mutableStateOf(false)

    var country by mutableStateOf("")
    var location by mutableStateOf("")
    var timezones by mutableStateOf(emptyList<String>())
    var countryCode by mutableIntStateOf(0)

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    fun checkPhoneNumber() {

        isError = false
        resultMessage = ""

        country = ""
        location = ""
        timezones = emptyList()
        countryCode = 0

        viewModelScope.launch {
            try {
                val response = sendPhoneNumberToAPI(phoneNumber)
                if (!response.is_valid) {
                    isError = true
                    resultMessage = wrongNumberMessage
                } else {

                    country = response.country
                    location = response.location.takeIf { it.isNotBlank() && it != response.country } ?: ""
                    timezones = response.timezones
                    countryCode = response.country_code

                    resultMessage = yippie
                }
            } catch (e: Exception) {
                isError = true
                resultMessage = "${e.message} :("
            }
        }
    }

    private suspend fun sendPhoneNumberToAPI(phoneNumber: String): PhoneCheckResponse {
        val response: HttpResponse = client.get("https://validate-phone-by-api-ninjas.p.rapidapi.com/v1/validatephone") {
            url { parameters.append("number", phoneNumber) }
            headers {
                append("X-RapidAPI-Key", "ce18eed194msh67a9acf1137b418p122bf1jsnc17f3679431d")
                append("X-RapidAPI-Host", "validate-phone-by-api-ninjas.p.rapidapi.com")
            }
        }
        return response.body()
    }
}


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