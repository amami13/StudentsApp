package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentsapp.model.Model

class StudentDetailsActivity : AppCompatActivity() {

    private var studentId: String? = null
    private var nameTextView: TextView? = null
    private var idTextView: TextView? = null
    private var phoneTextView: TextView? = null
    private var addressTextView: TextView? = null
    private var checkBox: CheckBox? = null
    private var editButton: Button? = null

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
        // Initialize views
        nameTextView = findViewById(R.id.student_details_name_text_view)
        idTextView = findViewById(R.id.student_details_id_text_view)
        phoneTextView = findViewById(R.id.student_details_phone_text_view)
        addressTextView = findViewById(R.id.student_details_address_text_view)
        checkBox = findViewById(R.id.student_details_check_box)
        editButton = findViewById(R.id.student_details_edit_button)

        // Retrieve the student ID passed via intent
        studentId = intent.getStringExtra("STUDENT_ID") ?: return

        // Set up Edit button listener
        editButton?.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("STUDENT_ID", studentId)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh student details
        loadStudentDetails()
    }

    private fun loadStudentDetails() {
        val student = studentId?.let { Model.shared.getStudentById(it) }
        if (student != null) {
            // Populate the UI with the student's details
            nameTextView?.text = "name: ${student.name}"
            idTextView?.text = "id: ${student.id}"
            phoneTextView?.text = "phone: ${student.phone}"
            addressTextView?.text = "address: ${student.address}"
            checkBox?.isChecked = student.isChecked
        } else {
            // If the student is not found, close the activity
            finish()
        }
    }
}