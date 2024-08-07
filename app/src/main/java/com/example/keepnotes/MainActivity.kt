package com.example.keepnotes

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.keepnotes.activities.AddNotesActivity
import com.example.keepnotes.adapters.FragmentAdapter
import com.example.keepnotes.adapters.NotesAdapter
import com.example.keepnotes.databinding.ActivityMainBinding
import com.example.keepnotes.models.NotesModel
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var notes: MutableList<NotesModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        notes = mutableListOf()

        initClick()

        binding.mainViewPager2.adapter = FragmentAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.mainViewPager2) { tab, position ->
            when (position) {
                0 -> tab.text = "All Notes"
                1 -> tab.text = "Urgent"
                2 -> tab.text = "High"
                3 -> tab.text = "Normal"
            }
        }
    }

    fun initClick() {
        binding.addNotesBtn.setOnClickListener {
            startActivity(Intent(this, AddNotesActivity::class.java))
        }
        binding.menuImageNote.setOnClickListener {
            showMenu(v = it)
        }
    }

    fun showMenu(v: View?) {
        var popupMenu = android.widget.PopupMenu(this, v)
        popupMenu.inflate(R.menu.popup_menu)
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener {

            when (it.itemId) {
                R.id.recycle_bin -> {
                    startActivity(Intent(this, RecycleBinActivity::class.java))
                    true
                }

                R.id.lightTheme -> {
                    savedTheme(0)
                    true
                }

                R.id.darkTheme -> {
                    savedTheme(1)
                    true
                }

                else -> {

                    true
                }
            }
        }
    }

//    override fun onResume() {
//        var db = DBSLite(this)
//        notes = db.readData()
//        rvAdapter.changeData(notes)
//        super.onResume()
//    }

    private fun savedTheme(index: Int) {

        var editor = getSharedPreferences("theme", MODE_PRIVATE).edit()
        editor.putInt("theme", index)
        editor.apply()
        finish()
        startActivity(intent)
    }
}