package com.example.practicalapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Redirect to LoginActivity (app starts at LoginActivity per manifest)
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
