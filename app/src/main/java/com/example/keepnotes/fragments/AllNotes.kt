package com.example.keepnotes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.keepnotes.DBSLite
import com.example.keepnotes.R
import com.example.keepnotes.adapters.NotesAdapter
import com.example.keepnotes.databinding.FragmentAllNotesBinding
import com.example.keepnotes.models.NotesModel

class AllNotes : Fragment() {

    private var allNotes = mutableListOf<NotesModel>()
    private var allNotesAdapter: NotesAdapter? = null
    private lateinit var binding: FragmentAllNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAllNotesBinding.inflate(inflater, container, false)

        allNotesAdapter = NotesAdapter(allNotes)
        binding.allNotesRecyclerView.adapter = allNotesAdapter

        return binding.root
    }

    override fun onResume() {
        var db = DBSLite(requireContext())
        allNotes = db.readData()
        allNotesAdapter!!.changeData(allNotes)
        super.onResume()
    }

}