package com.example.studentsapp.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Update
import com.example.studentsapp.model.Student


@Dao
interface StudentDao {
    @Insert
    fun addStudent(student: Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("SELECT * FROM Student")
    fun getAllStudents(): List<Student>

    @Query("UPDATE Student SET isChecked = :isChecked WHERE id = :id")
    fun updateCheckStatus(id: String, isChecked: Boolean)
}
