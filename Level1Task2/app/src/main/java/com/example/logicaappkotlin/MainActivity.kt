package com.example.logicaappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

        private var correctAnswers: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnAnswer.setOnClickListener{answerCheck()}
    }

    private fun answerCheck(){
        val answer1 = etAnswer1.text.toString()
        val answer2 = etAnswer2.text.toString()
        val answer3 = etAnswer3.text.toString()
        val answer4 = etAnswer4.text.toString()

        if(answer1 == getString(R.string.tvT)){
            correctAnswers++
        }
        if(answer2 == getString(R.string.tvF)){
            correctAnswers++
        }
        if(answer3 == getString(R.string.tvF)){
            correctAnswers++
        }
        if(answer4 == getString(R.string.tvF)){
            correctAnswers++
        }

        resultMessage()
        correctAnswers = 0
    }

    private fun resultMessage(){
        Toast.makeText(this, getString(R.string.correctAnswers, correctAnswers), Toast.LENGTH_LONG).show()
    }
}
