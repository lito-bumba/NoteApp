package com.litobumba.noteapp.presentation.note_add_edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.litobumba.noteapp.data.local.Note
import com.litobumba.noteapp.databinding.FragmentNoteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNote : Fragment() {

    private lateinit var binding: FragmentNoteBinding
    private val viewModel: AddNoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btSave.setOnClickListener {
            viewModel.addNote(
                Note(
                    title = binding.etTitle.text.toString(),
                    description = binding.etDescription.text.toString()
                )
            )
        }
    }
}