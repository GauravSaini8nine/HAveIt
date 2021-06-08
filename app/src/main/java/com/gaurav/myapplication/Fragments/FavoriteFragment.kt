package com.gaurav.myapplication.Fragments

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.gaurav.myapplication.Adaptor.FavRecyclerAdaptor
import com.gaurav.myapplication.Database.FAvDatabase
import com.gaurav.myapplication.Database.FavDataEntity
import com.gaurav.myapplication.R


class FavoriteFragment : Fragment() {
    lateinit var favrecyclerview: RecyclerView
    lateinit var favlinearlayoutmanager : RecyclerView.LayoutManager
    lateinit var favrecyclerAdaptor: FavRecyclerAdaptor
    var favrestlist = listOf<FavDataEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        favrecyclerview = view.findViewById(R.id.fav_rest_recycler_view)
        favlinearlayoutmanager = LinearLayoutManager(context)

        favrestlist = FavAsync(activity as Context).execute().get()
        Log.d("async"," ${favrestlist}")


        if (activity != null){
            favrecyclerAdaptor = FavRecyclerAdaptor(activity as Context, favrestlist)

            favrecyclerview.adapter = favrecyclerAdaptor
            favrecyclerview.layoutManager = favlinearlayoutmanager

        }





        return view
    }

    class FavAsync(val context: Context): AsyncTask<Void,Void,List<FavDataEntity>>(){
        override fun doInBackground(vararg params: Void?): List<FavDataEntity> {
            val db = Room.databaseBuilder(context,FAvDatabase::class.java,"rest-db" ).build()

            return db.DatabaseDao().getallrest()
        }

    }


}