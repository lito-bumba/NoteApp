package com.litobumba.noteapp.presentation.note_add_edit

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.litobumba.noteapp.data.local.Note
import com.litobumba.noteapp.databinding.NoteScreenBinding
import com.litobumba.noteapp.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteScreen : AppCompatActivity(){

    private lateinit var binding: NoteScreenBinding
    private val viewModel: AddNoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NoteScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBackNoteScreen.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            navigateUpTo(intent)
        }
    }

    override fun onDestroy() {
        if (valid()) {
            viewModel.addNote(
                Note(
                    title = if (!binding.etTitle.text.toString().isNullOrEmpty())
                        binding.etTitle.text.toString()
                    else "Novo Anotação",
                    description = binding.etDescription.text.toString()
                )
            )
        }
        super.onDestroy()
    }

    private fun valid(): Boolean {
        return !(binding.etTitle.text.isNullOrEmpty() &&
                binding.etDescription.text.isNullOrEmpty())
    }
}