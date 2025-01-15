package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.adapter.StudentsRecyclerAdapter
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

interface OnItemClickListener {
    fun onItemClick(position: Int)
    fun onItemClick(student: Student?)
}

class StudentsRecyclerViewActivity : AppCompatActivity() {

    private var students: MutableList<Student>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_students_list_view)

        // Adjust padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Load students data
        students = Model.shared.students

        // Initialize RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.students_recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Set up adapter
        val adapter = StudentsRecyclerAdapter(students)
        adapter.listener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                Log.d("TAG", "Item clicked at position: $position")
            }

            override fun onItemClick(student: Student?) {
                Log.d("TAG", "Student clicked: ${student?.name}")
                student?.let {
                    val intent = Intent(this@StudentsRecyclerViewActivity, StudentDetailsActivity::class.java)
                    intent.putExtra("STUDENT_ID", it.id) // Pass the selected student's ID
                    startActivity(intent)
                }
            }
        }
        recyclerView.adapter = adapter

        // Handle Add Student button click
        findViewById<android.widget.ImageButton>(R.id.addStudentButton).setOnClickListener {
            // Launch AddStudentActivity
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh the student list when returning to this activity
        students = Model.shared.students
        findViewById<RecyclerView>(R.id.students_recycler_view).adapter?.notifyDataSetChanged()
    }
}
