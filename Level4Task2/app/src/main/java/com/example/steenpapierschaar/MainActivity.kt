package com.example.steenpapierschaar

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.core.net.UriCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_score_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.random.Random

const val HISTORY_GAMES_REQUEST_CODE = 100

class MainActivity : AppCompatActivity() {

    private val historyGames = arrayListOf<Game>()
    private val gameAdapter = GameAdapter(historyGames)
    private lateinit var gameRepository : GameRepository

    private var computerMove: Int = 0
    private var playerMove: Int = 0
    private var totalWinsComputer: Int = 0
    private var totalWinsPlayer: Int = 0
    private var totalDraws: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameRepository = GameRepository(this)

        initViews()

    }

    private fun initViews(){
        getStats()
        updateUI()
        btnPaper.setOnClickListener{ playPaper() }
        btnRock.setOnClickListener{ playRock() }
        btnScissors.setOnClickListener{ playScissors() }
    }

    private fun updateUI(){
        tvStats.text = getString(R.string.test, totalWinsPlayer.toString(),totalDraws.toString(),totalWinsComputer.toString())
    }

    private fun getStats(){
        CoroutineScope(Dispatchers.Main).launch {
            val countPcWins = withContext(Dispatchers.IO){
                gameRepository.getLosses()
            }

            val countPlayerWins = withContext(Dispatchers.IO){
                gameRepository.getWins()
            }
            val countDraws = withContext(Dispatchers.IO){
                gameRepository.getDraws()
            }

            this@MainActivity.totalWinsComputer = countPcWins
            this@MainActivity.totalWinsPlayer = countPlayerWins
            this@MainActivity.totalDraws = countDraws
            this@MainActivity.updateUI()
            this@MainActivity.gameAdapter.notifyDataSetChanged()

        }
    }

    private fun addGamesToDatabase(){
        CoroutineScope(Dispatchers.Main).launch {
            val games = Game(
                winner = tvResult.text.toString(),
                computerMove = computerMove,
                playerMove = playerMove,
                date = Date().toString()

            )
            withContext(Dispatchers.IO){
                gameRepository.insertGame(games)
            }
        }
    }

    private fun playPaper(){
        ivPlayer.setImageResource(R.drawable.paper)
        playerMove = 1
        generateComputerMove()
        playGame(playerMove, computerMove)
    }
    private fun playRock(){
        ivPlayer.setImageResource(R.drawable.rock)
        playerMove = 2
        generateComputerMove()
        playGame(playerMove, computerMove)
    }
    private fun playScissors(){
        ivPlayer.setImageResource(R.drawable.scissors)
        playerMove = 3
        generateComputerMove()
        playGame(playerMove, computerMove)
    }

    private fun generateComputerMove(){
        computerMove = (1..3).random()
        when(computerMove){
            1 -> ivComputer.setImageResource(R.drawable.paper)
            2 -> ivComputer.setImageResource(R.drawable.rock)
            3 -> ivComputer.setImageResource(R.drawable.scissors)
        }
    }

    private fun playGame(playerMove: Int, computerMove: Int){

        if (computerMove == playerMove){
            tvResult.text = "Draw!"
            totalDraws++
        }
        else if (computerMove == 1 && playerMove == 2){
            tvResult.text = "Computer Wins!"
            totalWinsComputer++
        }
        else if (computerMove == 1 && playerMove == 3){
            tvResult.text = "You Win!"
            totalWinsPlayer++
        }
        else if (computerMove == 2 && playerMove == 1){
            tvResult.text = "You Win!"
            totalWinsPlayer++
        }
        else if (computerMove == 2 && playerMove == 3){
            tvResult.text = "Computer Wins!"
            totalWinsComputer++
        }
        else if (computerMove == 3 && playerMove == 1){
            tvResult.text = "Computer Wins!"
            totalWinsComputer++
        }
        else if (computerMove == 3 && playerMove == 2){
            tvResult.text = "You Win!"
            totalWinsPlayer++
        }
        addGamesToDatabase()
        updateUI()
        //getStats()
    }

    private fun gotoHistory(){
        val intent = Intent(this,ScoreHistoryActivity::class.java)
        startActivityForResult(intent, HISTORY_GAMES_REQUEST_CODE)
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                HISTORY_GAMES_REQUEST_CODE -> {
                    getStats()
                    updateUI()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_gotoHistory -> {
                gotoHistory()
                true
            }

        else -> super.onOptionsItemSelected(item)
        }


    }
}
