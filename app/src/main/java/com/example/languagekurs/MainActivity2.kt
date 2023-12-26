package com.example.languagekurs

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.database.*

class MainActivity2 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val note: TextView = findViewById(R.id.notes)

        val _db = FirebaseDatabase.getInstance()
        val refDb = _db.getReference("note")

        refDb.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val stringBuilder = StringBuilder()
                for (rec in snapshot.children){
                    val opT = rec.child("openT").getValue(String::class.java)
                    val clT = rec.child("closeT").getValue(String::class.java)
                    val deT = rec.child("descT").getValue(String::class.java)

                    stringBuilder.append("Открытый текст: $opT\n")
                    stringBuilder.append("Закрытый текст: $clT\n")
                    stringBuilder.append("Описание: $deT\n")
                    stringBuilder.append("\n")
                }
                val _dbValues = stringBuilder.toString()
                note.text = _dbValues
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity2, "Ошибка", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun buttonaddNote(view: View) {
        val buttonaddNote = Intent(this, MainActivity::class.java)
        startActivity(buttonaddNote)
    }
}