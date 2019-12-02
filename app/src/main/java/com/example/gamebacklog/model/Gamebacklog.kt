package com.example.gamebacklog.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity//(tableName = "Gamebacklog")
data class Gamebacklog(

    //@ColumnInfo(name = "title")
    var title: String,

    //@ColumnInfo(name = "platform")
    var platform: String,

    //@ColumnInfo(name = "day")
    var day: String,

    //@ColumnInfo(name = "month")
    var month: String,

    //@ColumnInfo(name = "year")
    var year: String,

    @PrimaryKey var id: Long? = null
) : Parcelable