package com.example.studentsapp.model

class Model private constructor() {

    // Array list of students
    val students: MutableList<Student> = ArrayList()

    companion object {
        val shared = Model()
    }

    init {

        // Create an Array for 20 mock students
        for (i in 0..20) {
            val student = Student(
                id = "$i",
                name = "Name $i",
                phone = "0549999999",
                address = "Ben Gurion $i Tel Aviv",
                isChecked = false
            )
            students.add(student)
        }
    }

    // Add a new student
    fun addStudent(student: Student) {
        students.add(student)
    }

    // Get all students
    fun getAllStudents(): List<Student> {
        return students
    }

    // Update an existing student and return if update was successful
    fun updateStudent(updatedStudent: Student): Boolean {
        val index = students.indexOfFirst { it.id == updatedStudent.id }
        return if (index != -1) {
            students[index] = updatedStudent
            true
        } else {
            false
        }
    }

    // Delete a student and return if the removal was successful
    fun deleteStudent(studentId: String): Boolean {
        val index = students.indexOfFirst { it.id == studentId }
        return if (index != -1) {
            students.removeAt(index)
            true
        } else {
            false
        }
    }

    // Get a single student by ID
    fun getStudentById(studentId: String): Student? {
        return students.find { it.id == studentId }
    }

    // Toggle the 'isChecked' status of a student and return if action was successful
    fun toggleStudentChecked(studentId: String): Boolean {
        val index = students.indexOfFirst { it.id == studentId }
        return if (index != -1) {
            val student = students[index]
            students[index] = student.copy(isChecked = !student.isChecked)
            true
        } else {
            false
        }
    }
}
