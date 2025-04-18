package com.example.homework2.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.homework2.model.Note
import com.example.homework2.ui.theme.*
import com.example.homework2.viewmodel.NoteViewModel
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.input.pointer.pointerInput

@Composable
fun HomeScreen(navController: NavController, viewModel: NoteViewModel) {
    val notes by viewModel.allNotes.collectAsState(initial = emptyList())

    Column(modifier = Modifier.fillMaxSize().padding(MediumPadding)) {
        Text("Note App :3", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(MediumPadding))

        LazyColumn {
            items(notes) { note ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onLongPress = {
                                    viewModel.deleteNote(note)
                                },
                                onTap = {
                                    navController.navigate("NoteDetail/${note.id}")
                                }
                            )
                        },
                    colors = CardDefaults.cardColors(containerColor = LittlePookieGreen)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = note.title, style = MaterialTheme.typography.titleLarge)
                        Text(text = note.text, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}
