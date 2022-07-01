package com.litobumba.noteapp.domain.repository

import com.litobumba.noteapp.data.local.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getAll(): Flow<List<Note>>

    fun getTotal(): Flow<Long>

    suspend fun getNote(id: Int): Note?

    fun getNote(title: String): Flow<Note?>

    suspend fun insert(noteDto: Note)

    suspend fun delete(noteDto: Note)

}