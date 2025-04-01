package com.example.homework1.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import com.example.homework1.data.PurchaseRepository
import com.example.homework1.ui.res.Homework1Theme
import com.example.homework1.ui.res.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Homework1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(MediumPadding)
                    ) {
                        UpperText(name = "<   Credit card")
                        Spacer(modifier = Modifier.height(MediumPadding))

                        val repository = PurchaseRepository()
                        val purchases = repository.getPurchases(10)

                        Check(purchases = purchases)
                    }
                }
            }
        }
    }
}
