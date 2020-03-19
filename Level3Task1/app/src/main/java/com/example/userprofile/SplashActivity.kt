/*
Applicatie Level 3 Task 1
Deze app laat de gebruiker een profiel aanmaken. in het profiel staan naam,
achternaam en een foto uit de galerij van de telefoon.

Gemaakt door: Leroy Gabriëlse
Student nr.: 500761062
e-mail: leroy.gabrielse@hva.nl

*/

package com.example.userprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(
                Intent(
                    this@SplashActivity,
                    CreateProfileActivity::class.java
                )
            )
            finish()
        }, 1000)

    }
}
