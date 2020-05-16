package com.example.gamebacklog.ui.main

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamebacklog.R
import com.example.gamebacklog.adapter.GameAdapter
import com.example.gamebacklog.model.Game
import com.example.gamebacklog.ui.add.ActivityAdd
import com.example.gamebacklog.ui.add.EXTRA_GAME

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

const val ADD_GAME_REQUEST_CODE = 100

class MainActivity : AppCompatActivity() {

    private val games = arrayListOf<Game>()
    private val gameAdapter = GameAdapter(games)

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initViews()
        observeViewModel()
    }

    private fun initViews(){
        rvGames.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvGames.adapter = gameAdapter
        createItemTouchHelper().attachToRecyclerView(rvGames)

        fab.setOnClickListener { view ->
            val intent = Intent(this, ActivityAdd::class.java)
            startActivityForResult(intent,
                ADD_GAME_REQUEST_CODE
            )
        }
    }
    private fun observeViewModel(){
        viewModel.games.observe(this, Observer {games->
            this@MainActivity.games.clear()
            this@MainActivity.games.addAll(games)
            gameAdapter.notifyDataSetChanged()
        })
    }
    private fun createItemTouchHelper():ItemTouchHelper{
        val callback = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT){

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val gameToDelete = games[position]
                viewModel.deleteGame(gameToDelete)

            }

        }
        return(ItemTouchHelper((callback)))
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ADD_GAME_REQUEST_CODE -> {
                    data?.let {safeData ->
                        val reminder = safeData.getParcelableExtra<Game>(EXTRA_GAME)

                        reminder?.let { safeGame ->
                            viewModel.insertGame(safeGame)
                        } ?: run {
                            Log.e("test", "game is null")
                        }
                    } ?: run {
                        Log.e("test", "null intent data received")
                    }
                }
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
