package com.example.personalnotepad.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.personalnotepad.database.NoteRepo

class MainActivityViewModel (application: Application) : AndroidViewModel(application){
    private val noteRepository = NoteRepo(application.applicationContext)

    val note = noteRepository.getNotepad()

}