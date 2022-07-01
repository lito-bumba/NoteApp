package com.litobumba.noteapp.presentation.note_list

import androidx.lifecycle.*
import com.litobumba.noteapp.data.local.Note
import com.litobumba.noteapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private var _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes

    init {
        getNotes()
    }

    private fun getNotes() {
        useCases.getNotes().onEach { notes ->
            _notes.value = notes
        }.launchIn(viewModelScope)
    }

    private var _note = MutableLiveData<Note?>()
    val note: LiveData<Note?> = _note

    fun getNote(id: Int = 0, title: String = "") {
        useCases.getNote(id, title).onEach {
            _note.value = it
        }.launchIn(viewModelScope)
    }

    private var _totalNotes = MutableLiveData<Long>(0)
    val totalNotes: LiveData<Long> = _totalNotes

    fun getCount() {
        useCases.getTotal().onEach { total ->
            _totalNotes.value = total
        }.launchIn(viewModelScope)
    }

    fun addNote(note: Note) {
        viewModelScope.launch {
            useCases.addNote(note)
        }
    }
}