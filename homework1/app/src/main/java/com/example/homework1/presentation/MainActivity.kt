package com.example.homework1.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.homework1.data.PurchaseRepository
import com.example.homework1.ui.theme.Homework1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Homework1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(16.dp)
                    ) {
                        UpperText(name = "<   Credit card")
                        Spacer(modifier = Modifier.height(16.dp))

                        val repository = PurchaseRepository()
                        val purchases = repository.getPurchases(10)

                        Check(purchases = purchases)
                    }
                }
            }
        }
    }
}
