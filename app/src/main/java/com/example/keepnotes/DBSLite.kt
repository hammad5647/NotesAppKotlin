package com.example.keepnotes

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf
import com.example.keepnotes.models.NotesModel

class DBSLite(
    context: Context,
) : SQLiteOpenHelper(context, "Data.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        var table =
            "CREATE TABLE notes(id INTEGER PRIMARY KEY AUTOINCREMENT,note TEXT, title TEXT, date TEXT, color TEXT)"
        db!!.execSQL(table)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    fun insertData(model: NotesModel) {
        var db = writableDatabase
        var query =
            "INSERT INTO notes ( note, title, date, color) VALUES ( '${model.note}', '${model.title}', '${model.date}','${model.color}')"
        db.execSQL(query)
    }

    @SuppressLint("Range")
    fun readData(): MutableList<NotesModel> {

        var list = mutableListOf<NotesModel>()
        var db = readableDatabase
        var query = ("SELECT * FROM notes")
        var cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                var title = cursor.getString(cursor.getColumnIndex("title"))
                var note = cursor.getString(cursor.getColumnIndex("note"))
                var color = cursor.getInt(cursor.getColumnIndex("color"))
                var date = cursor.getString(cursor.getColumnIndex("date"))
                var id = cursor.getInt(cursor.getColumnIndex("id"))


                var model = NotesModel(title = title, note = note, color = "", date = date, id = id
                )
                list.add(model)

            } while (cursor.moveToNext())
        }
        return list
    }

    fun deleteData(id: Int) {
        var db = writableDatabase
        var query = "DELETE FROM notes WHERE id = '${id}' "
        db.execSQL(query)
    }

    fun updateData(model:NotesModel) {
        var db = writableDatabase
        var cn = contentValuesOf()
        cn.put("title",model.title)
        cn.put("note", model.note)
        cn.put("date", model.date)
        cn.put("color", model.color)
        db.update("notes", cn, "id = ?", arrayOf<String>("${model.id}"))

        db.close()
    }
}
