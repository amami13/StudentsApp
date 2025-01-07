package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

class StudentDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)
        enableEdgeToEdge()

        // Apply insets for modern UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.student_details_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get references to views
        val nameTextView: TextView = findViewById(R.id.student_details_name_text_view)
        val idTextView: TextView = findViewById(R.id.student_details_id_text_view)
        val phoneTextView: TextView = findViewById(R.id.student_details_phone_text_view)
        val addressTextView: TextView = findViewById(R.id.student_details_address_text_view)
        val checkBox: CheckBox = findViewById(R.id.student_details_check_box)
        val editButton: Button = findViewById(R.id.student_details_edit_button)

        // Retrieve the student data passed via intent
//        val studentId = intent.getStringExtra("STUDENT_ID") ?: return
        val studentId = "1" // change to above
        val student = Model.shared.getStudentById(studentId)

        if (student != null) {
            // Populate the views with the student's data
            nameTextView.text = student.name
            idTextView.text = "ID: ${student.id}"
            phoneTextView.text = "phone: ${student.phone}"
            addressTextView.text = "address: ${student.address}"
            checkBox.isChecked = student.isChecked
        } else {
            // If no student is found, exit the activity
            finish()
            return
        }

        // Handle CheckBox toggle
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            Model.shared.toggleStudentChecked(studentId)
        }

        // Edit button click listener
        editButton.setOnClickListener {
            Log.i("hi", "bye");
//            val intent = Intent(this, EditStudentActivity::class.java)
//            intent.putExtra("STUDENT_TO_EDIT", student)
//            startActivity(intent)
        }
    }
}
