package com.example.boodschappenreminder

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.math.MathContext

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class BoodschappenLijstRoomDatabase : RoomDatabase(){

    abstract fun productDao() : ProductDao

    companion object{
        private const val DATABASE_NAME = "BOODSCHAPPENLIJST_DATABASE"
        @Volatile
        private var BoodschappenLijstInstance : BoodschappenLijstRoomDatabase? = null

        fun getDatabase(context: Context) : BoodschappenLijstRoomDatabase? {
            if (BoodschappenLijstInstance == null){
                synchronized(BoodschappenLijstRoomDatabase::class.java){
                    if (BoodschappenLijstInstance == null){
                        BoodschappenLijstInstance = Room.databaseBuilder(context.applicationContext, BoodschappenLijstRoomDatabase::class.java, DATABASE_NAME).build()
                    }
                }
            }
            return BoodschappenLijstInstance
        }
    }

}