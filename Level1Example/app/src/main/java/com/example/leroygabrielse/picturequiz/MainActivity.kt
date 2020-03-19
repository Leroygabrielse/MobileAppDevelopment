package com.example.leroygabrielse.picturequiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{
            checkAnswer()
        }

    }

    //Checkt of het antwoord klopt
    private fun checkAnswer(){
        val answer = etAnswer.text.toString()

        if (answer == getString(R.string.giraffe)){
            Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this, getString(R.string.fasle), Toast.LENGTH_LONG).show()
        }
    }

}
