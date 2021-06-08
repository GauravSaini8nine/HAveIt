package com.gaurav.myapplication.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface FavDAO {
    @Insert
    fun favinsert(favDataEntity: FavDataEntity)


    @Delete
    fun favdelete(favDataEntity: FavDataEntity)


    @Query("select * from FAVrest")
    fun getallrest():List<FavDataEntity>


    @Query("select * from FAVrest where rest_id = :restid")
    fun getrestbyID(restid:String):FavDataEntity
}