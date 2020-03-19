package com.example.swipequiz

data class Question(
    var questionText: String,
    var answerBoolean: String
){
    companion object {
        val QUESTIONS = arrayOf(
            "1 + 1 = 2",
            "3 + 4 = 8",
            "5 * 6 = 32",
            "4 / 2 = 2"
        )

        val ANSWERS = arrayOf(
            "True",
            "False",
            "False",
            "True"
        )
    }
}

