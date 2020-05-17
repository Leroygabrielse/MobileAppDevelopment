package com.example.gamebacklog.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.time.LocalDate
import java.util.*

@Parcelize
@Entity(tableName = "gameTable")
data class Game (
    var title: String,
    var platforms: String,
    var releaseDate: LocalDate,
    @PrimaryKey var id: Long? = null
) : Parcelable