package com.example.trivia

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initViews()
        initViewModel()
    }

    private fun initViews() {
        fab.setOnClickListener {
            viewModel.getRandomTrivia() // Get a random number trivia when the fab is clicked.
        }
    }

    private fun initViewModel() {
        // Initialize the MainActivityViewModel.
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        // Observe the trivia object.
        viewModel.trivia.observe(this, Observer {
            tvTrivia.text = it?.text
        })

        // Observe the error message.
        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }
}

