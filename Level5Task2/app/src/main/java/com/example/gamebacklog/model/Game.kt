package com.example.gamebacklog.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity
data class Game (
    var title: String,
    var platforms: String,
    var releaseDate: Date,
    @PrimaryKey var id: Long? = null
) : Parcelable