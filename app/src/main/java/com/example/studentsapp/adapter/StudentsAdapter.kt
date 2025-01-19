package com.example.studentsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.OnItemClickListener
import com.example.studentsapp.R
import com.example.studentsapp.model.Student

class StudentsAdapter(private val students: MutableList<Student>?): RecyclerView.Adapter<StudentViewHolder>() {
    var listener: OnItemClickListener? = null
    override fun getItemCount(): Int = students?.size ?: 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.student_list_row,
            parent,
            false
        )
        return StudentViewHolder(itemView, listener)
    }
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(students?.get(position), position)
    }
}