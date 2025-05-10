package com.example.homework4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.example.homework4.view.PhoneNumberCheck
import com.example.homework4.viewModel.PhoneCheckViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val phoneCheckViewModel: PhoneCheckViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                PhoneNumberCheck(viewModel = phoneCheckViewModel)
            }
        }
    }
}
