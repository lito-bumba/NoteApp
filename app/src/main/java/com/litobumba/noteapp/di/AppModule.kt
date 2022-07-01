package com.litobumba.noteapp.di

import android.app.Application
import androidx.room.Room
import com.litobumba.noteapp.data.local.NoteDB
import com.litobumba.noteapp.data.local.NoteDao
import com.litobumba.noteapp.data.repository.NoteRepositoryImpl
import com.litobumba.noteapp.domain.repository.NoteRepository
import com.litobumba.noteapp.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDB(app: Application): NoteDB {
        return Room.databaseBuilder(
            app,
            NoteDB::class.java,
            NoteDB.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteDao(app: NoteDB): NoteDao {
        return app.noteDao
    }

    @Provides
    @Singleton
    fun provideRepositoryImpl(dao: NoteDao): NoteRepositoryImpl {
        return NoteRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: NoteRepositoryImpl): UseCases
    {
        return UseCases(
            getNotes = GetNotes(repository),
            getNote = GetNote(repository),
            getTotal = GetTotal(repository),
            addNote = AddNote(repository)
        )
    }
}