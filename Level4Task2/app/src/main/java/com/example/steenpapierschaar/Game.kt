package com.example.steenpapierschaar

import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName="history_table")
data class Game(

    @PrimaryKey(autoGenerate=true)
    @ColumnInfo(name = "id")
    var id: Long? = null,

    @ColumnInfo(name = "winner")
    var winner: String,

    @ColumnInfo(name = "computermove")
    var computerMove: Int,

    @ColumnInfo(name = "playermove")
    var playerMove: Int,

    @ColumnInfo(name = "datum")
    var date: String

    /*
    winner      :string
    totalwins   :int
    totaldraws  :int
    totalwinspc :int
    url pc      :int converting to corresponding img
    url player  :int ""
    date        :Date
    */

) : Parcelable