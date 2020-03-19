package com.example.steenpapierschaar

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Game::class], version = 1, exportSchema = false)
abstract class GamesRoomDatabase : RoomDatabase(){

    abstract fun gameDao() : GameDao

    companion object{

        private const val DATABASE_NAME = "GAMEHISTORY"

        @Volatile
        private var gameInstance : GamesRoomDatabase? = null

        fun getDatabase(context: Context) : GamesRoomDatabase? {
            if (gameInstance == null){
                synchronized(GamesRoomDatabase::class.java){
                    if (gameInstance == null){
                       gameInstance = Room.databaseBuilder(context.applicationContext, GamesRoomDatabase::class.java,
                           DATABASE_NAME).build()
                    }
                }
            }
            return gameInstance
        }

    }

}


