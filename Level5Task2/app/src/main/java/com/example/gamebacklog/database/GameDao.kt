package com.example.gamebacklog.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.gamebacklog.model.Game

@Dao
interface GameDao {

    @Query("SELECT * FROM gameTable")
    fun getAllGames() : LiveData<List<Game>>

    @Insert
    suspend fun insertGame(game: Game)

    @Delete
    suspend fun deleteGame(game: Game)

    @Update
    suspend fun updateGame(game: Game)
}