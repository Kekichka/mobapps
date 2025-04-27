package com.example.homework4.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.example.homework4.ui.theme.*
import com.example.homework4.viewModel.PhoneCheckViewModel

@Composable
fun PhoneNumberCheck(viewModel: PhoneCheckViewModel) {
    var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MediumPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text(phone) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PookieGreen,
                focusedLabelColor = PookieGreen,
                unfocusedBorderColor = PookieGreen,
                unfocusedLabelColor = PookieGreen
            )
        )

        Spacer(modifier = Modifier.height(MediumPadding))

        if (viewModel.isError) {
            Text(
                text = viewModel.resultMessage,
                color = MaterialTheme.colorScheme.error
            )

            Spacer(modifier = Modifier.height(MediumPadding))

            Button(
                onClick = {
                    viewModel.phoneNumber = phoneNumber.text
                    viewModel.resultMessage = ""
                    viewModel.isError = false
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = PookieGreen)
            ) {
                Text(retry)
            }
        } else {
            Button(
                onClick = {
                    viewModel.phoneNumber = phoneNumber.text
                    viewModel.checkPhoneNumber()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = PookieGreen)
            ) {
                Text(checkPhone)
            }

            Spacer(modifier = Modifier.height(MediumPadding))

            Text(viewModel.resultMessage)

            if (!viewModel.country.isBlank()) {
                Spacer(modifier = Modifier.height(MediumPadding))
                Text("$country ${viewModel.country}")
            }

            if (viewModel.location.isNotBlank()) {
                Spacer(modifier = Modifier.height(MediumPadding))
                Text("$loca ${viewModel.location}")
            }

            if (viewModel.timezones.isNotEmpty()) {
                Spacer(modifier = Modifier.height(MediumPadding))
                Text("$timezone ${viewModel.timezones}")
            }

            if (viewModel.countryCode != 0) {
                Spacer(modifier = Modifier.height(MediumPadding))
                Text("$countryCode ${viewModel.countryCode}")
            }
        }
    }
}
