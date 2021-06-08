package com.gaurav.myapplication.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase


@Entity( tableName = "FAVrest")
data class FavDataEntity (

    @PrimaryKey
    val rest_id : String,

    @ColumnInfo()
    val rest_name : String,

    @ColumnInfo
    val rest_rating : String,

    @ColumnInfo
    val rest_imageurl : String,

    @ColumnInfo
    val rest_cost_for_one : String


    )