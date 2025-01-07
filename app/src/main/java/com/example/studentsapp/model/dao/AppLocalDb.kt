package com.example.studentsapp.model.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Room
import com.example.studentsapp.base.StudentsApp

@Database(entities = [Student::class], version = 2)
abstract class AppLocalDbRepository : RoomDatabase() {
    abstract fun studentDao(): StudentDao
}

object AppLocalDb {
    val database: AppLocalDbRepository by lazy {
        val context = StudentsApp.Globals.context ?: throw IllegalStateException("Application context missing")
        Room.databaseBuilder(
            context = context,
            klass = AppLocalDbRepository::class.java,
            name = "student_database"
        ).fallbackToDestructiveMigration().build()
    }
}
