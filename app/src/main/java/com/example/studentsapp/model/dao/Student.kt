package com.example.studentsapp.model.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    @PrimaryKey val id: String,
    val name: String,
    val phone: Int,
    val address: String,
    val isChecked: Boolean
)
