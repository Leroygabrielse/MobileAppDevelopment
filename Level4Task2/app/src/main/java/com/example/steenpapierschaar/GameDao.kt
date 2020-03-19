package com.example.steenpapierschaar

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GameDao {

    @Query("SELECT * FROM history_table")
    suspend fun getAllGames(): List<Game>

    @Insert
    suspend fun insertGame(game: Game)

    @Query("DELETE FROM history_table")
    suspend fun deleteAllGames()

    @Query("SELECT COUNT(winner) FROM history_table WHERE winner='Computer Wins!'")
    suspend fun getLosses(): Int

    @Query("SELECT COUNT(winner) FROM history_table WHERE winner ='Draw!'")
    suspend fun getDraws(): Int

    @Query("SELECT COUNT(winner) FROM history_table WHERE winner ='You Win!'")
    suspend fun getWins(): Int

}