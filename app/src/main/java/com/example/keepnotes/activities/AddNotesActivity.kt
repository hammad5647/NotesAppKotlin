package com.example.keepnotes.activities

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.keepnotes.DBSLite
import com.example.keepnotes.databinding.ActivityAddNotesBinding
import com.example.keepnotes.models.NotesModel

class AddNotesActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddNotesBinding
    var intentids: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        initClick()
    }

    private fun initClick() {
        intentData()
        binding.saveNoteBtn.setOnClickListener {
            var title = binding.notesTitleTxt.text.toString()
            var note = binding.notesNoteTxt.text.toString()
            var notes =
                NotesModel(id = intentids, note = note, title = title, date = "", color = "")
            var db = DBSLite(this)

            if (intentids == -1) db.insertData(notes)
            else db.updateData(notes)
            finish()
        }
        binding.notesBackBtn.setOnClickListener {
            finish()
        }
    }
    private fun intentData() {
        var intent = getIntent()

        var title = intent.getStringExtra("title")
        var note = intent.getStringExtra("note")

        intentids = intent.getIntExtra("id", -1)
        binding.notesTitleTxt.setText(title)
        binding.notesNoteTxt.setText(note)
    }
}