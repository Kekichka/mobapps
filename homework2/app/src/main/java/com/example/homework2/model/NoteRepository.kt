package com.example.homework2.model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NoteRepository {
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes = _notes.asStateFlow()

    fun addNote(note: Note) {
        _notes.value = _notes.value + note
    }

}