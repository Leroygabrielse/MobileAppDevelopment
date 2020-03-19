package com.example.steenpapierschaar

import android.content.Context

class GameRepository (context: Context){

    private val gameDao: GameDao

    init {
        val database = GamesRoomDatabase.getDatabase(context)
        gameDao = database!!.gameDao()
    }

    suspend fun getAllGames(): List<Game> {
        return gameDao.getAllGames()
    }

    suspend fun insertGame(game: Game) {
        gameDao.insertGame(game)
    }

    suspend fun deleteAllGames() {
        gameDao.deleteAllGames()
    }
    suspend fun getLosses(): Int{
        return gameDao.getLosses()
    }
    suspend fun getWins(): Int{
        return gameDao.getWins()
    }
    suspend fun getDraws(): Int{
        return gameDao.getDraws()
    }
}