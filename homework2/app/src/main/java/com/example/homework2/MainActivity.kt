package com.example.homework2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.homework2.model.NoteRepository
import com.example.homework2.view.Navigation
import com.example.homework2.view.*
import com.example.homework2.viewmodel.*
import com.example.homework2.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val repository = NoteRepository()
        val viewModel = NoteViewModel(repository)

        setContent {
            Homework2Theme {
                val navController = rememberNavController()

                Scaffold(
                    floatingActionButton = {
                        val currentDestination by navController.currentBackStackEntryAsState()
                        if (currentDestination?.destination?.route == "HomeScreen") {
                            ExtendedFloatingActionButton(
                                onClick = { navController.navigate("AddNoteScreen") },
                                icon = { Icon(Icons.Default.Add, contentDescription = "Add", tint = White) },
                                text = { Text("Add a Note", color = White) },
                                containerColor = PookieGreen,
                                modifier = Modifier.padding(MediumPadding)
                            )
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Navigation(navController, viewModel, Modifier.padding(innerPadding))
                }
            }
        }
    }
}
