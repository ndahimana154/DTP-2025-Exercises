package com.example.practicalapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnFirestore = findViewById<Button>(R.id.btnFirestore)
        btnFirestore.setOnClickListener {
            startActivity(Intent(this, FirestoreActivity::class.java))
        }
    }
}
