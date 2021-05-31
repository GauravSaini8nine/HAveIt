package com.gaurav.myapplication.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.gaurav.myapplication.Adaptor.HomeRecyclerAdaptor
import com.gaurav.myapplication.Model.HomeData
import com.gaurav.myapplication.R


class HomeFragment : Fragment() {

    lateinit var home_recycler_view: RecyclerView
    lateinit var home_linealayout_manager: LinearLayoutManager

    val homedatalist = arrayListOf<HomeData>(
        HomeData("hey","jhgjh", "jhgj", "jhgjh"),
        HomeData("hey","jhgjh", "jhgj", "jhgjh"),
        HomeData("hey","jhgjh", "jhgj", "jhgjh"),
        HomeData("hey","jhgjh", "jhgj", "jhgjh"),
        HomeData("hey","jhgjh", "jhgj", "jhgjh"),

    )
    lateinit var homerecyclerviewAdaptor: HomeRecyclerAdaptor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        home_recycler_view = view.findViewById(R.id.home_recycler_view)
        home_linealayout_manager = LinearLayoutManager(activity)
        homerecyclerviewAdaptor = HomeRecyclerAdaptor(activity as Context, homedatalist)
        home_recycler_view.adapter = homerecyclerviewAdaptor
        home_recycler_view.layoutManager = home_linealayout_manager


        val homeQuesue = Volley.newRequestQueue(activity as Context)
        val homeUrl = "http://13.235.250.119/v2/restaurants/fetch_result/"

        val homejsonrequest = object :
            JsonObjectRequest(
                Method.GET,
                homeUrl,
                null,
                Response.Listener {
                                  print(it)
////                    println(" xxxxxxxx $it")
//                    Log.d("xxx", "this is $it")
//                    println(it)
//                    val dataa = it.getJSONArray("data")
//                    val dataaa = it.getJSONObject("data")
//                    println("zzzzzzzz  $it")
//                    println("xxxxxxxx  $dataaa")
////                    val success = it.getBoolean("success")
////                    println(" xxxxxxxx $success ")
////
////                    if (success) {
////                        val homerecivedata = it.getJSONArray("data")
////                        for (i in 0 until homerecivedata.length()) {
////                            val homejsonobject = homerecivedata.getJSONObject(i)
////                            val homeobject = HomeData(
////                                homejsonobject.getString("name"),
////                                homejsonobject.getString("rating"),
////                                homejsonobject.getString("cost_for_one"),
////                                homejsonobject.getString("image_url")
////
////                            )
////                            homedatalist.add(homeobject)
////
////
//                            homerecyclerviewAdaptor = HomeRecyclerAdaptor(activity as Context, homedatalist)
//                            home_recycler_view.adapter = homerecyclerviewAdaptor
//                            home_recycler_view.layoutManager = home_linealayout_manager
////
////                        }
////
////                    } else {
////                        Toast.makeText(activity, "someting Happened wrong", Toast.LENGTH_SHORT)
////                            .show()
////                    }
                },
                Response.ErrorListener {

                }

            ) {
            override fun getHeaders(): MutableMap<String, String> {
                val header = HashMap<String,String>()
                header["Content-type"] = "application/json"
                header["token"] = "c605abb5fa2d21"
                return header
            }

        }
        homeQuesue.add(homejsonrequest)




        return view


    }

}