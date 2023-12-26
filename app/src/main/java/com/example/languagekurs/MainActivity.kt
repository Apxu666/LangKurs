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
    private var counter = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val _db = FirebaseDatabase.getInstance()
        val refDb = _db.getReference("note")
        val buttonSave : Button = findViewById(R.id.buttonSave)

        buttonSave.setOnClickListener {

            val textOpen : EditText = findViewById(R.id.textopen)
            val textClose : EditText = findViewById(R.id.textclose)
            val textDesc : EditText = findViewById(R.id.textdesc)

            val textO = textOpen.text.toString()
            val textC = textClose.text.toString()
            val textD = textDesc.text.toString()

            if (!TextUtils.isEmpty(textO) && !TextUtils.isEmpty(textC)) {
                val newNotes = refDb.child("rec$counter")
                newNotes.child("openT").setValue(textO)
                newNotes.child("closeT").setValue(textC)
                newNotes.child("descT").setValue(textD)

                textOpen.setText("")
                textClose.setText("")
                textDesc.setText("")

                Toast.makeText(this, "Запись добавлена успешно " + counter, Toast.LENGTH_SHORT).show()
                counter++
            } else {
                Toast.makeText(this, "Пустое поле", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun showNotes(view: View) {
        val showNotes = Intent(this, MainActivity2::class.java)
        startActivity(showNotes)
    }
}