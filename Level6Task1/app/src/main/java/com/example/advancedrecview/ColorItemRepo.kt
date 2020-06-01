package com.example.advancedrecview

class ColorItemRepo {
    fun getColorItems(): List<ColorItem>{
        return arrayListOf(
            ColorItem("000000", "Black"),
            ColorItem("FF0000", "Red"),
            ColorItem("FFFF00", "Yellow"),
            ColorItem("000800", "Green"),
            ColorItem("E3E3E3", "Whitish")
        )
    }
}