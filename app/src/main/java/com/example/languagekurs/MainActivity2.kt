package com.example.languagekurs

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.database.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener

class MainActivity2 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val enterText : EditText = findViewById(R.id.enterText)
        val openT : TextView = findViewById(R.id.openView)
        val closeT : TextView = findViewById(R.id.closeView)
        val descT : TextView = findViewById(R.id.descView)
        val readNote : Button = findViewById(R.id.readnote)
        val imageButton : ImageButton = findViewById(R.id.btnShow)

        readNote.setOnClickListener {
            val database = FirebaseDatabase.getInstance().reference
            val noteRef = database.child("note")
            noteRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (child in dataSnapshot.children) {
                        val openValue = child.child("openT").getValue(String::class.java)
                        val closeValue = child.child("closeT").getValue(String::class.java)
                        val descValue = child.child("descT").getValue(String::class.java)

                        openT.text = openValue
                        closeT.text = closeValue
                        descT.text = descValue

                        enterText.text.clear()
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@MainActivity2, "Ошибка", Toast.LENGTH_SHORT).show()
                }
            })
        }

        imageButton.setOnClickListener {
            if (closeT.visibility == View.VISIBLE) {
                closeT.visibility = View.INVISIBLE
            } else {
                closeT.visibility = View.VISIBLE
            }
        }
    }

    fun buttonaddNote(view: View) {
        val buttonaddNote = Intent(this, MainActivity::class.java)
        startActivity(buttonaddNote)
    }
}