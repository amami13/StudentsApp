package com.example.studentsapp.model

class Model private constructor() {

    val students: MutableList<Student> = ArrayList()

    companion object {
        val shared = Model()
    }


    // Add a new student
    fun addStudent(student: Student) {
        students.add(student)
    }

    // Get all students
    fun getAllStudents(): List<Student> {
        return students.toList() // Return a copy to prevent modification
    }

    // Update an existing student
    fun updateStudent(updatedStudent: Student): Boolean {
        val index = students.indexOfFirst { it.id == updatedStudent.id }
        return if (index != -1) {
            students[index] = updatedStudent
            true
        } else {
            false
        }
    }

    // Delete a student
    fun deleteStudent(studentId: String): Boolean {
        return students.removeIf { it.id == studentId }
    }

    // Get a single student by ID
    fun getStudentById(studentId: String): Student? {
        return students.find { it.id == studentId }
    }

    // Toggle the 'isChecked' status of a student
    fun toggleStudentChecked(studentId: String): Boolean {
        val student = students.find { it.id == studentId }
        return if (student != null) {
            val updatedStudent = student.copy(isChecked = !student.isChecked)
            updateStudent(updatedStudent)
            true
        } else {
            false
        }
    }
}