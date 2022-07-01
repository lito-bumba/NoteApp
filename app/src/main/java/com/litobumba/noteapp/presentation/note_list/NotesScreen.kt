package com.litobumba.noteapp.presentation.note_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.litobumba.noteapp.AdapterNote
import com.litobumba.noteapp.R
import com.litobumba.noteapp.data.local.Note
import com.litobumba.noteapp.databinding.FragmentNotesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesScreen : Fragment() {

    private lateinit var binding: FragmentNotesBinding
    private var adapter = AdapterNote()
    private val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotesBinding.inflate(inflater)
        binding.rvNotes.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.notes.observe(viewLifecycleOwner) { notes ->
            adapter.addList(notes)
        }

        binding.fabList.setOnClickListener {
            viewModel.addNote(
                Note(
                    title = "Lito Bumba",
                    description = "This note was added by UI Screen"
                )
            )

            viewModel.getCount()
            viewModel.totalNotes.observe(viewLifecycleOwner) {
                Toast.makeText(
                    binding.root.context,
                    it.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_main_to_note)

            /*viewModel.getNote(7)

            viewModel.note.observe(viewLifecycleOwner){
                Toast.makeText(
                    binding.root.context,
                    it.toString(),
                    Toast.LENGTH_LONG
                ).show()
            }*/
        }
    }
}