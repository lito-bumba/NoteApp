package com.litobumba.noteapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.litobumba.noteapp.data.local.Note
import com.litobumba.noteapp.data.local.NoteDao
import com.litobumba.noteapp.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(private val dao: NoteDao) : NoteRepository {

    override fun getAll(): Flow<List<Note>> {
        return dao.getAll()
    }

    override fun getTotal(): Flow<Long> {
        return dao.getTotal()
    }

    override suspend fun getNote(id: Int): Note? {
        return dao.getById(id)
    }

    override fun getNote(title: String): Flow<Note?> {
        return dao.getByTitle(title)
    }

    override suspend fun insert(noteDto: Note) {
        dao.insert(noteDto)
    }

    override suspend fun delete(noteDto: Note) {
        dao.delete(noteDto)
    }

}