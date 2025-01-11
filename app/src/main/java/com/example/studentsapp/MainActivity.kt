package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Automatically navigate to StudentsListActivity
        val intent = Intent(this, StudentsListActivity::class.java)
        startActivity(intent)

        // Finish MainActivity so the user cannot return to it
        finish()
    }
}
