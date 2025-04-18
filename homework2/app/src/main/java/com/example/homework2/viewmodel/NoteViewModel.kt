package com.example.homework2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework2.model.Note
import com.example.homework2.model.NoteRepository
import com.example.homework2.model.NoteDB
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val noteDao = NoteDB.getDatabase(application).noteDao()
    private val repository = NoteRepository(noteDao)

    val allNotes = repository.allNotes

    fun addNote(note: Note) {
        viewModelScope.launch {
            repository.addNote(note)
        }
    }

    fun editNote(note: Note) {
        viewModelScope.launch {
            repository.editNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }

    suspend fun getNoteById(id: Int): Flow<Note?> {
        return repository.getNoteById(id)
    }
}
