package com.example.steenpapierschaar

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_score_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScoreHistoryActivity : AppCompatActivity() {

    private val historyGames = arrayListOf<Game>()
    private val gameAdapter = GameAdapter(historyGames)
    private lateinit var gameRepository : GameRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_history)

        gameRepository = GameRepository(this)

        initViews()
    }

    private fun initViews(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Game History"
        rvScores.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvScores.adapter = gameAdapter
        rvScores.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        getGamesFromDatabase()

    }

    private fun deleteHistory(){
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO){
                gameRepository.deleteAllGames()
            }
            getGamesFromDatabase()
        }
    }

    private fun getGamesFromDatabase(){
        CoroutineScope(Dispatchers.Main).launch {
            val uitslagenList = withContext(Dispatchers.IO){
                gameRepository.getAllGames()
            }
            this@ScoreHistoryActivity.historyGames.clear()
            this@ScoreHistoryActivity.historyGames.addAll(uitslagenList)
            this@ScoreHistoryActivity.gameAdapter.notifyDataSetChanged()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_deleteHistory -> {
                deleteHistory()
                true
            }
            android.R.id.home -> {
                val resultIntent = Intent()
                setResult(Activity.RESULT_OK,resultIntent)
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_history, menu)
        return true
    }
}
