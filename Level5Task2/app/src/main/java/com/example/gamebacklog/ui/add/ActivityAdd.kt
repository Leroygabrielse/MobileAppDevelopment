package com.example.gamebacklog.ui.add

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.gamebacklog.R
import com.example.gamebacklog.database.Converters
import com.example.gamebacklog.model.Game
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.content_activity_add.*
import java.text.DateFormat
import java.time.LocalDate
import androidx.lifecycle.Observer
import java.time.format.DateTimeFormatter

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


            if(etTitle.text.toString().isNotBlank() && etPlatform.text.toString().isNotBlank()){

                try {
                    var day = etDay.text.toString().toInt()
                    var month = etMonth.text.toString().toInt()
                    var year = etYear.text.toString().toInt()
                    val date = LocalDate.of(year, month, day)
                    val game = Game(etTitle.text.toString(), etPlatform.text.toString(), date)
                    val resultIntent = Intent()
                    resultIntent.putExtra(EXTRA_GAME, game)
                    setResult(Activity.RESULT_OK, resultIntent)
                    finish()
                }catch (DateFormatInvalidException: Exception){
                    Toast.makeText(this, "Not a valid date!", Toast.LENGTH_SHORT).show()
                }

            }else {
                Toast.makeText(this, "Fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun initViewModel(){
        addViewModel = ViewModelProvider(this).get(ActivityAddViewModel::class.java)
        addViewModel.game.observe(this, Observer { game ->
            if (game != null){
                etTitle.setText(game.title)
                etPlatform.setText(game.platforms)
            }
        })
        addViewModel.error.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })

        addViewModel.success.observe(this, Observer { success ->
            if (success!!) finish()
        })

    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun isValidDate(date: LocalDate): Boolean {
        try {
            LocalDate.parse(date.toString(), DateTimeFormatter.ofPattern("dd MMMM yy"))
        }catch(DateTimeException: Exception){
            return false
        }
        return true
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