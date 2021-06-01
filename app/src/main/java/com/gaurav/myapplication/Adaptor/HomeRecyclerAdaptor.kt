package com.gaurav.myapplication.Adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gaurav.myapplication.Model.HomeData
import com.gaurav.myapplication.R
import com.squareup.picasso.Picasso

class HomeRecyclerAdaptor(val context: Context, val itemList : ArrayList<HomeData>)
    : RecyclerView.Adapter<HomeRecyclerAdaptor.HomeViewHolder>(){
    class HomeViewHolder(view:View): RecyclerView.ViewHolder(view){
        val homerestname : TextView = view.findViewById(R.id.home_rest_name)
        val homerestprice : TextView = view.findViewById(R.id.home_rest_price)
        val homerestrating : TextView = view.findViewById(R.id.home_rest_rating)
        val homerestimage : ImageView = view.findViewById(R.id.home_image)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_recycler_structure, parent,false)
        return  HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val text = itemList[position]
        holder.homerestname.text = text.rest_name
        holder.homerestprice.text = text.rest_cost_for_one
        holder.homerestrating.text = text.rest_Rating
        Picasso.get().load(text.rest_image_url).into(holder.homerestimage)

    }

    override fun getItemCount(): Int {
        return itemList.size
    }


}