package com.example.gamebacklog.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.gamebacklog.database.GameRepo
import com.example.gamebacklog.model.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application): AndroidViewModel(application) {

    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val gameRepo = GameRepo(application.applicationContext)

    val games: LiveData<List<Game>> = gameRepo.getAllGames()

    fun insertGame(game: Game){
        ioScope.launch {
            gameRepo.insertGame(game)
        }
    }
    fun deleteGame(game: Game){
        ioScope.launch {
            gameRepo.deleteGame(game)
        }
    }
    fun deleteAllGames(){
        ioScope.launch {
            gameRepo.deleteAllGames()
        }
    }

}