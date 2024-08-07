package com.example.keepnotes.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.RecyclerView
import com.example.keepnotes.DBSLite
import com.example.keepnotes.R
import com.example.keepnotes.activities.AddNotesActivity
import com.example.keepnotes.databinding.NotesSampleBinding
import com.example.keepnotes.models.NotesModel

class NotesAdapter(var notes: MutableList<NotesModel>) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = NotesSampleBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {

        var view = LayoutInflater.from(parent.context).inflate(R.layout.notes_sample, parent, false)

        return NotesViewHolder(view)
    }

    override fun getItemCount(): Int {

        return notes.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.binding.noteSmplTitle.text = notes[position].title
        holder.binding.noteSmplNote.text = notes[position].note

        holder.binding.deleteNoteImage.setOnClickListener{
            var db = DBSLite(holder.itemView.context)
            db.deleteData(notes[position].id)
            notes.removeAt(position)
            notifyDataSetChanged()
        }
        holder.binding.sampleNote.setOnClickListener{
            var intent = Intent(holder.itemView.context, AddNotesActivity::class.java)
            intent.putExtra("id", notes[position].id)
            intent.putExtra("title", notes[position].title)
            intent.putExtra("note", notes[position].note)
            holder.itemView.context.startActivity(intent)

        }
        if(notes[position].priority==1)
            holder.binding.priorityNotesColor.setBackgroundColor(holder.itemView.context.getColor(R.color.red))
        if (notes[position].priority==2)
            holder.binding.priorityNotesColor.setBackgroundColor(holder.itemView.context.getColor(R.color.orange))
        if(notes[position].priority==1)
            holder.binding.priorityNotesColor.setBackgroundColor(holder.itemView.context.getColor(R.color.lightorange))
        if (notes[position].priority==2)
            holder.binding.priorityNotesColor.setBackgroundColor(holder.itemView.context.getColor(R.color.green))
    }

    @SuppressLint("NotifyDataSetChanged")
    fun changeData(n1: MutableList<NotesModel>) {
        notes = n1
        notifyDataSetChanged()
    }
}
