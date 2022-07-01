package com.litobumba.noteapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getAll(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getById(id: Int): Note?

    @Query("SELECT * FROM note WHERE title = :title")
    fun getByTitle(title: String): Flow<Note?>

    @Query("SELECT COUNT(id) FROM note")
    fun getTotal(): Flow<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(noteDto: Note)

    @Update
    suspend fun update(noteDto: Note)

    @Delete
    suspend fun delete(noteDto: Note)

    @Query("DELETE FROM note")
    suspend fun deleteAll()

}