package com.example.advancedrecview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private val colorItemRepo = ColorItemRepo()

    val colorItems = MutableLiveData<List<ColorItem>>().apply {
        value = colorItemRepo.getColorItems()
    }
}