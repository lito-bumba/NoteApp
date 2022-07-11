package com.litobumba.noteapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.litobumba.noteapp.data.local.Note

class AdapterNote() : RecyclerView.Adapter<AdapterNote.ViewHolder>() {

    private var notes: List<Note> = emptyList()
    fun addList(notes: List<Note>){
        this.notes = notes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleNote.text = notes[position].title
        holder.descriptionNote.text = notes[position].description
    }

    override fun getItemCount(): Int = notes.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleNote = itemView.findViewById<TextView>(R.id.tvTitle)
        val descriptionNote = itemView.findViewById<TextView>(R.id.tvDescription)

        init {
            itemView.setOnClickListener {
                val id = notes[adapterPosition].id
                Toast.makeText(itemView.context, id.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}