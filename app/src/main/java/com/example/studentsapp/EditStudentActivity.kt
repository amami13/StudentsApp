package com.example.studentsapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

class EditStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)
        enableEdgeToEdge()

        // Apply insets for modern UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.edit_student_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Get references to views
        val nameEditText: EditText = findViewById(R.id.edit_student_name_edit_text)
        val idEditText: EditText = findViewById(R.id.edit_student_id_edit_text)
        val phoneEditText: EditText = findViewById(R.id.edit_student_phone_edit_text)
        val addressEditText: EditText = findViewById(R.id.edit_student_address_edit_text)
        val checkedCheckBox: CheckBox = findViewById(R.id.edit_student_checked_checkbox)
        val cancelButton: Button = findViewById(R.id.edit_student_cancel_button)
        val deleteButton: Button = findViewById(R.id.edit_student_delete_button)
        val saveButton: Button = findViewById(R.id.edit_student_save_button)

        // Retrieve the student to edit
        val studentId = intent.getStringExtra("STUDENT_ID") ?: return
        val student = Model.shared.getStudentById(studentId)

        if (student != null) {
            // Populate the fields with existing data
            nameEditText.setText(student.name)
            idEditText.setText(student.id)
            phoneEditText.setText(student.phone)
            addressEditText.setText(student.address)
            checkedCheckBox.isChecked = student.isChecked
        }

        // Cancel button logic
        cancelButton.setOnClickListener {
            finish()
        }

        // Delete button logic
        deleteButton.setOnClickListener {
            Model.shared.deleteStudent(studentId)
            finish()
        }

        // Save button logic
        saveButton.setOnClickListener {
            val updatedStudent = Student(
                id = idEditText.text.toString(),
                name = nameEditText.text.toString(),
                phone = phoneEditText.text.toString(),
                address = addressEditText.text.toString(),
                isChecked = checkedCheckBox.isChecked
            )
            Model.shared.updateStudent(updatedStudent)
            finish()
        }
    }

}
