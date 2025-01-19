package com.example.studentsapp

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddStudentActivity : AppCompatActivity() {

    private var studentImageView: ImageView? = null
    private var nameEditText: EditText? = null
    private var idEditText: EditText? = null
    private var phoneEditText: EditText? = null
    private var addressEditText: EditText? = null
    private var checkedCheckBox: CheckBox? = null
    private var cancelButton: Button? = null
    private var saveButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.add_student_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        studentImageView = findViewById(R.id.add_student_image_view)
        nameEditText = findViewById(R.id.add_student_name_edit_text)
        idEditText = findViewById(R.id.add_student_id_edit_text)
        phoneEditText = findViewById(R.id.add_student_phone_edit_text)
        addressEditText = findViewById(R.id.add_student_address_edit_text)
        checkedCheckBox = findViewById(R.id.add_student_checked_checkbox)
        cancelButton = findViewById(R.id.add_student_cancel_button)
        saveButton = findViewById(R.id.add_student_save_button)

        // Set static image for the student
        studentImageView?.setImageResource(R.drawable.avatar)

        // Cancel button click
        cancelButton?.setOnClickListener {
            finish()
        }

        // Save button click
        saveButton?.setOnClickListener {
            saveStudent()
        }
    }

    private fun saveStudent() {
        val name = nameEditText?.text.toString()
        val id = idEditText?.text.toString()
        val phone = phoneEditText?.text.toString()
        val address = addressEditText?.text.toString()
        val isChecked = checkedCheckBox?.isChecked ?: false

        // Ensure that fields are not empty
        if (name.isEmpty() || id.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, "All fields must be filled!", Toast.LENGTH_SHORT).show()
            return
        }

        val newStudent = Student(
            name = name,
            id = id,
            phone = phone,
            address = address,
            isChecked = isChecked,
        )
        Model.shared.addStudent(newStudent)
        Toast.makeText(this, "Student added successfully", Toast.LENGTH_SHORT).show()
        finish()
    }
}
