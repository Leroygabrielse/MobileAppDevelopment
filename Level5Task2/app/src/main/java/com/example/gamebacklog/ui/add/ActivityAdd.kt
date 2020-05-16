package com.example.gamebacklog.ui.add

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gamebacklog.R
import com.example.gamebacklog.model.Game

import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.content_activity_add.*
import java.util.*

const val EXTRA_GAME = "EXTRA_GAME"

class ActivityAdd : AppCompatActivity() {

    private lateinit var addViewModel: ActivityAddViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Add Game"

        initViews()
        //initViewModel()

    }

    private fun initViews(){
        fab.setOnClickListener { view ->
            if(etTitle.text.toString().isNotBlank() && etPlatform.text.toString().isNotBlank()){
                val game = Game(etTitle.text.toString(), etPlatform.text.toString())
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
/*
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
 */