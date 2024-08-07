package com.example.keepnotes.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.keepnotes.fragments.AllNotes
import com.example.keepnotes.fragments.HighNotes
import com.example.keepnotes.fragments.MediumNotes
import com.example.keepnotes.fragments.NormalNotes
import com.example.keepnotes.fragments.UrgentNotes

class FragmentAdapter(fragmentView: FragmentActivity) : FragmentStateAdapter(fragmentView) {
    override fun getItemCount(): Int {
        return 5
    }
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AllNotes()
            1 -> UrgentNotes()
            2 -> HighNotes()
            3 -> NormalNotes()
            4 -> MediumNotes()
            else -> AllNotes()

        }
    }
}