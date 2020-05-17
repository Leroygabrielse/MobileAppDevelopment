package com.example.gamebacklog.ui.add

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.gamebacklog.R
import com.example.gamebacklog.database.Converters
import com.example.gamebacklog.model.Game
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.content_activity_add.*
import java.text.DateFormat
import java.time.LocalDate

import java.util.*
import kotlin.math.log

const val EXTRA_GAME = "EXTRA_GAME"

class ActivityAdd : AppCompatActivity() {

    private lateinit var addViewModel: ActivityAddViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Add Game"

        initViews()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initViews(){

        fab.setOnClickListener { view ->

            var day = etDay.text.toString().toInt()
            var month = etMonth.text.toString().toInt() - 1
            var year = etYear.text.toString().toInt()

            val date = LocalDate.of(year, month, day)
            if(etTitle.text.toString().isNotBlank() && etPlatform.text.toString().isNotBlank()){
                val game = Game(etTitle.text.toString(), etPlatform.text.toString(), date)
                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_GAME, game)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}