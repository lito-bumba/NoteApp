package com.litobumba.noteapp.domain.use_cases

data class UseCases(
    val getNotes: GetNotes,
    val getNote: GetNote,
    val getTotal: GetTotal,
    val addNote: AddNote
)
