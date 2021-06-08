package com.gaurav.myapplication.Adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gaurav.myapplication.Database.FavDataEntity
import com.gaurav.myapplication.R
import com.squareup.picasso.Picasso

class FavRecyclerAdaptor(val context: Context, val itemList: List<FavDataEntity>) :
    RecyclerView.Adapter<FavRecyclerAdaptor.FavViewHolder>() {
    class FavViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val favrestname: TextView = view.findViewById(R.id.fav_rest_name)
        val favrestprice: TextView = view.findViewById(R.id.fav_rest_price)
        val favrestrating: TextView = view.findViewById(R.id.fav_rest_rating)
        val favrestimage: ImageView = view.findViewById(R.id.fav_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.favorite_recycler_structure, parent, false)
        return FavViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        val text = itemList[position]
        holder.favrestname.text = text.rest_name
        holder.favrestprice.text = text.rest_cost_for_one
        holder.favrestrating.text = text.rest_rating
        Picasso.get().load(text.rest_imageurl).into(holder.favrestimage)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}