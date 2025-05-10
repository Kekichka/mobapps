package com.example.homework2.view

import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.homework2.model.Note
import com.example.homework2.ui.theme.*
import com.example.homework2.viewmodel.NoteViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NoteDetailScreen(navController: NavController, noteId: Int?, viewModel: NoteViewModel = viewModel()) {
    var isEditing by remember { mutableStateOf(false) }
    var editableTitle by remember { mutableStateOf("") }
    var editableText by remember { mutableStateOf("") }
    var currentNote by remember { mutableStateOf<Note?>(null) }

    LaunchedEffect(noteId) {
        noteId?.let { id ->
            viewModel.getNoteById(id).collect { note ->
                currentNote = note
                note?.let {
                    editableTitle = it.title
                    editableText = it.text
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MediumPadding),
    ) {
        if (isEditing) {
            OutlinedTextField(
                value = editableTitle,
                onValueChange = { editableTitle = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(SmallPadding))
            OutlinedTextField(
                value = editableText,
                onValueChange = { editableText = it },
                label = { Text("Text") },
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            Text(text = currentNote?.title ?: "", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(SmallPadding))
            Text(text = currentNote?.text ?: "", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(SmallPadding))

            currentNote?.timeStamp?.let { timestamp ->
                val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                val date = Date(timestamp)
                Text(
                    text = "Created on: ${dateFormat.format(date)}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
        Spacer(modifier = Modifier.height(MediumPadding))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FloatingActionButton(
                onClick = {
                    if (isEditing && currentNote != null) {
                        viewModel.editNote(currentNote!!.copy(title = editableTitle, text = editableText))
                    }
                    isEditing = !isEditing
                },
                containerColor = PookieGreen,
                contentColor = White,
                shape = RoundedCornerShape(RoundMedium),
            ) {
                Text(if (isEditing) "Save" else "Edit")
            }

            FloatingActionButton(
                onClick = { navController.popBackStack() },
                containerColor = PookieGreen,
                contentColor = White,
                shape = RoundedCornerShape(RoundMedium),
            ) {
                Text("Back")
            }
        }
    }
}
