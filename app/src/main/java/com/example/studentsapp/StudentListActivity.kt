package com.example.studentsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.model.Model
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.content.Intent

class StudentsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_students_list_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val addStudentButton: Button = findViewById(R.id.add_student_button)
        addStudentButton.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java) // Intent to open the AddStudentActivity
            startActivity(intent)
        }

        val studentsRecyclerView: RecyclerView = findViewById(R.id.students_recycler_view)

        // Get the list of students from the model
        val students = Model.shared.getAllStudents()

        // Set up the adapter and recycler view
        studentsRecyclerView.adapter = StudentsAdapter(this, students)

    }

    override fun onResume() {
        super.onResume()

        val students = Model.shared.getAllStudents() // Fetch updated list
        val studentsRecyclerView: RecyclerView = findViewById(R.id.students_recycler_view)
        val adapter = studentsRecyclerView.adapter

        if (adapter is StudentsAdapter) {
            adapter.updateStudents(students)
        }
    }
}
