package com.example.homework2.viewmodel

import androidx.lifecycle.ViewModel
import com.example.homework2.model.Note
import com.example.homework2.model.NoteRepository
import kotlinx.coroutines.flow.StateFlow

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {
    val notes: StateFlow<List<Note>> = repository.notes

    fun addNote(title: String, text: String) {
        if (title.isNotBlank() && text.isNotBlank()) {
            repository.addNote(Note(title, text))
        }
    }
}
