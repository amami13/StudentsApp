package com.example.studentsapp.base

import android.app.Application
import android.content.Context

class StudentsApp: Application() {

    object Globals {
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()

        Globals.context = applicationContext
    }
}