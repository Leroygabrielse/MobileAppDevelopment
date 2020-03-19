/*
Applicatie Level 1 Task 1
Deze app laat de gebruiker gokken of de eerstvolgende dobbelsteenworp
hoger, lager of gelijk is aan de laatste worp. Het laat het resultaat zien door een plaatje van
de dobbelsteen met het aantal ogen en tekst.

Gemaakt door: Leroy Gabriëlse
Student nr.: 500761062
e-mail: leroy.gabrielse@hva.nl

*/

package com.example.higherlowergamble

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_higher_lower_gamble.*

class MainActivity : AppCompatActivity() {

    private var currentThrow: Int = 1
    private var lastThrow: Int = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_higher_lower_gamble)
        initView()
        btnHigher.setOnClickListener{onHigherClick()}
        btnLower.setOnClickListener{onLowerClick()}
        btnEqual.setOnClickListener{onEqualClick()}

    }

    private fun initView(){
        updateUI()
    }

    private fun updateUI(){
        tvLastThrow.text = getString(R.string.lastThrow, lastThrow)
        when (currentThrow){
            1 -> ivDice.setImageResource(R.drawable.dice1)
            2 -> ivDice.setImageResource(R.drawable.dice2)
            3 -> ivDice.setImageResource(R.drawable.dice3)
            4 -> ivDice.setImageResource(R.drawable.dice4)
            5 -> ivDice.setImageResource(R.drawable.dice5)
            6 -> ivDice.setImageResource(R.drawable.dice6)
        }
    }
    private fun rollDice() {
        lastThrow = currentThrow
        currentThrow = (1..6).random()
        updateUI()
    }

    private fun onAnswerCorrect(){
        Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_LONG).show()
    }
    private fun onAnswerIncorrect(){
        Toast.makeText(this, getString(R.string.incorrect), Toast.LENGTH_LONG).show()
    }
    private fun onHigherClick(){
        rollDice()

        if (currentThrow > lastThrow){
            onAnswerCorrect()
        }
        else{
            onAnswerIncorrect()
        }

    }
    private fun onLowerClick(){
        rollDice()

        if (currentThrow < lastThrow){
            onAnswerCorrect()
        }
        else{
            onAnswerIncorrect()
        }

    }
    private fun onEqualClick(){
        rollDice()

        if (currentThrow == lastThrow){
            onAnswerCorrect()
        }
        else{
            onAnswerIncorrect()
        }

    }


}
