package com.example.studentsapp.model

class Model private constructor() {

    // Hash map of students by ID
    private val students: MutableMap<String, Student> = HashMap()

    companion object {
        val shared = Model()
    }

    // Add a new student
    fun addStudent(student: Student) {
        students[student.id] = student
    }

    // Get all students
    fun getAllStudents(): List<Student> {
        return students.values.toList()
    }

    // Update an existing student and return if update was successful
    fun updateStudent(updatedStudent: Student): Boolean {
        if (students.containsKey(updatedStudent.id)) {
            students[updatedStudent.id] = updatedStudent
            return true
        } else {
            return false
        }
    }

    // Delete a student and return if the removal was successful
    fun deleteStudent(studentId: String): Boolean {
        return students.remove(studentId) != null
    }

    // Get a single student by ID
    fun getStudentById(studentId: String): Student? {
        return students[studentId]
    }

    // Toggle the 'isChecked' status of a student and return if action was successful
    fun toggleStudentChecked(studentId: String): Boolean {
        val student = students[studentId]
        if (student != null) {
            val updatedStudent = student.copy(isChecked = !student.isChecked)
            students[studentId] = updatedStudent
            return true
        } else {
            return false
        }
    }
}
