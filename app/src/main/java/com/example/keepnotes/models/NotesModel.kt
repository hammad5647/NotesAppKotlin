package com.example.keepnotes.models

import android.webkit.WebSettings.RenderPriority

data class NotesModel(
    var id: Int = 0,
    var priority: Int = 0,
    var note: String,
    var title: String,
    var date: String,
    var color: String
) {
}