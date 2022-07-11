package com.litobumba.noteapp.presentation.note_add_edit

import androidx.lifecycle.*
import com.litobumba.noteapp.data.local.Note
import com.litobumba.noteapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    fun addNote(note: Note) {
        viewModelScope.launch {
            useCases.addNote(note)
        }
    }
}