/*
Applicatie Level 1 Example
Deze applicatie laat de gebruiker een giraf zien,
Vervolgens moet hij in een veld invoeren wat voor dier het is.

Gemaakt door: Leroy Gabriëlse
Student nr.: 500761062
e-mail: leroy.gabrielse@hva.nl

*/

package com.example.leroygabrielse.picturequiz

//library imports
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    //Methode die wordt uitgevoard bij startup van de app
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Methode die een klik op de knop registreert
        button.setOnClickListener{
            checkAnswer()
        }

    }

    //Methode die invoer vergelijkt met 'giraffe' en feedback op het antwoord geeft met een toast.
    private fun checkAnswer(){
        val answer = etAnswer.text.toString().toLowerCase()

        if (answer == getString(R.string.giraffe)){
            Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this, getString(R.string.fasle), Toast.LENGTH_LONG).show()
        }
    }

}
