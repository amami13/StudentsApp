package com.example.studentsapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.model.Student

class StudentsAdapter(
    private val context: Context,
    private val students: List<Student>
) : RecyclerView.Adapter<StudentsAdapter.StudentViewHolder>() {

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.student_row_name_text_view)
        val idTextView: TextView = itemView.findViewById(R.id.student_row_id_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.student_list_row, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.nameTextView.text = student.name
        holder.idTextView.text = "ID: ${student.id}"

        // Set click listener to open details activity
        holder.itemView.setOnClickListener {
            val intent = Intent(context, StudentDetailsActivity::class.java)
            intent.putExtra("STUDENT_ID", student.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return students.size
    }
}
