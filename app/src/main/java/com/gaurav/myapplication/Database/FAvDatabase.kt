package com.gaurav.myapplication.Database

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase


@Database(entities = [FavDataEntity::class], version = 2)
abstract class FAvDatabase :RoomDatabase(){
    abstract fun DatabaseDao():FavDAO

}