package com.litobumba.noteapp.domain.use_cases

import com.litobumba.noteapp.data.local.Note
import com.litobumba.noteapp.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetNotes(private val repository: NoteRepository) {
    operator fun invoke(): Flow<List<Note>> {
        return repository.getAll()
    }
}

class GetNote(private val repository: NoteRepository) {
    operator fun invoke(id: Int = 0, title: String = ""): Flow<Note?> {

        if (!title.isNullOrEmpty())
            return repository.getNote(title)

        if (id > 0)
            return flow { emit(repository.getNote(id)) }

        else
            return flow {  }
    }
}

class AddNote(private val repository: NoteRepository) {
    suspend operator fun invoke(note: Note) {
        repository.insert(note)
    }
}

class GetTotal(private val repository: NoteRepository) {
    operator fun invoke(): Flow<Long> {
        return repository.getTotal()
    }
}