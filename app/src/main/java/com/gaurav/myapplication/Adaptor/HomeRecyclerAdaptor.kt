package com.gaurav.myapplication.Adaptor

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.gaurav.myapplication.Avtivity.DetailedActivity
import com.gaurav.myapplication.Database.FAvDatabase
import com.gaurav.myapplication.Database.FavDataEntity
import com.gaurav.myapplication.Model.HomeData
import com.gaurav.myapplication.R
import com.squareup.picasso.Picasso

class HomeRecyclerAdaptor(val context: Context, val itemList : ArrayList<HomeData>)
    : RecyclerView.Adapter<HomeRecyclerAdaptor.HomeViewHolder>() {


    class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val homerestname: TextView = view.findViewById(R.id.home_rest_name)
        val homerestprice: TextView = view.findViewById(R.id.home_rest_price)
        val homerestrating: TextView = view.findViewById(R.id.home_rest_rating)
        val homerestimage: ImageView = view.findViewById(R.id.home_image)
        val homefave: ImageView= view.findViewById(R.id.home_favorite_selection)




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.home_recycler_structure, parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val text = itemList[position]

        holder.homerestname.text = text.rest_name
        holder.homerestprice.text = text.rest_cost_for_one
        holder.homerestrating.text = text.rest_Rating
        Picasso.get().load(text.rest_image_url).into(holder.homerestimage)


            val favEntity = FavDataEntity(
                text.rest_id,
                text.rest_name,
                text.rest_Rating,
                text.rest_image_url,
                text.rest_cost_for_one
            )


            val checkFav = DBAsynctask(context, favEntity, 1).execute()
            val isFav = checkFav.get()
            if (isFav) {
                holder.homefave.setImageResource(R.drawable.faveremove)
            } else {
                holder.homefave.setImageResource(R.drawable.favadd)

            }

            holder.homefave.setOnClickListener {
                if (!DBAsynctask(context, favEntity, 1).execute()
                        .get()
                ) {
                    val async =
                        DBAsynctask(context, favEntity, 2).execute()
                    val result = async.get()
                    if (result) {
                        Toast.makeText(context, "added", Toast.LENGTH_SHORT).show()

                        holder.homefave.setImageResource(R.drawable.faveremove)
                    } else {
                        Toast.makeText(context, "something", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    val async =
                        DBAsynctask(context, favEntity, 3).execute()
                    val result = async.get()
                    if (result) {
                        Toast.makeText(context, "removed", Toast.LENGTH_SHORT).show()

                        holder.homefave.setImageResource(R.drawable.favadd)
                    } else {
                        Toast.makeText(context, "something", Toast.LENGTH_SHORT).show()
                    }

                }
                val async =
                DBAsynctask(context, favEntity, 4).execute().get()




            }

    }
    override fun getItemCount(): Int {
        return itemList.size
    }
    class DBAsynctask(val context: Context, val FavEntity: FavDataEntity, val mode: Int) :
        AsyncTask<Void, Void, Boolean>() {

        val db = Room.databaseBuilder(context, FAvDatabase::class.java, "rest-db").build()
        override fun doInBackground(vararg params: Void?): Boolean {

            when (mode) {
                1 -> {
                    val book: FavDataEntity? =
                        db.DatabaseDao().getrestbyID(FavEntity.rest_id)
                    db.close()
                    return book != null
                }
                2 -> {
                    db.DatabaseDao().favinsert(FavEntity)
                    db.close()
                    return true
                }
                3 -> {
                    db.DatabaseDao().favdelete(FavEntity)
                    db.close()
                    return true
                }
                4-> {

                    val s= db.DatabaseDao().getallrest().size
                    Log.d("sooooooo", "$s")
                    val d = db.DatabaseDao().getallrest()
                    Log.d("sooooooo", "$d")

                }
                
            }
            return false
        }

    }


}
