package com.example.studentsapp.adapter

import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.OnItemClickListener
import com.example.studentsapp.R
import com.example.studentsapp.model.Student
import com.example.studentsapp.model.Model

class StudentViewHolder(
    itemView: View,
    private val listener: OnItemClickListener?
): RecyclerView.ViewHolder(itemView) {
    private var nameTextView: TextView? = null
    private var idTextView: TextView? = null
    private var studentCheckBox: CheckBox? = null
    private var student: Student? = null
    init {
        nameTextView = itemView.findViewById(R.id.student_row_name_text_view)
        idTextView = itemView.findViewById(R.id.student_row_id_text_view)
        studentCheckBox = itemView.findViewById(R.id.student_row_check_box)

        studentCheckBox?.setOnClickListener {
            student?.let{
                Model.shared.toggleStudentChecked(it.id)
            }
        }

        itemView.setOnClickListener {
            Log.d("TAG", "On click listener on position $adapterPosition")
            listener?.onItemClick(student)
        }
    }

    fun bind(student: Student?, position: Int) {
        this.student = student
        nameTextView?.text = student?.name
        idTextView?.text = student?.id

        studentCheckBox?.apply {
            isChecked = student?.isChecked ?: false
            tag = position
        }
    }
}