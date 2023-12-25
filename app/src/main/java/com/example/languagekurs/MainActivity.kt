package com.example.languagekurs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var _db: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        _db = FirebaseDatabase.getInstance().reference

        val buttonSave : Button = findViewById(R.id.buttonSave)
        buttonSave.setOnClickListener {
            addNote()
        }
    }

    fun addNote(){
        val textOpen : EditText = findViewById(R.id.textopen)
        val textClose : EditText = findViewById(R.id.textclose)
        val textDesc : EditText = findViewById(R.id.textdesc)
        val note = Notes.create()

        note.openT = textOpen.text.toString()
        note.closeT = textClose.text.toString()
        note.descT = textDesc.text.toString()

        if (!TextUtils.isEmpty(note.openT) && !TextUtils.isEmpty(note.closeT)) {
            val newNotes = _db.child("note").push()
            note.noteId = newNotes.key

            newNotes.setValue(note)
            textOpen.setText("")
            textClose.setText("")
            textDesc.setText("")

            Toast.makeText(this, "Запись добавлена успешно " + note.noteId, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Пустое поле", Toast.LENGTH_SHORT).show()
        }
    }

    fun showNotes(view: View) {
        val showNotes = Intent(this, MainActivity2::class.java)
        startActivity(showNotes)
    }
}