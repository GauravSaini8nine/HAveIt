package com.gaurav.myapplication.Fragments

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.gaurav.myapplication.Adaptor.HomeRecyclerAdaptor
import com.gaurav.myapplication.Model.HomeData
import com.gaurav.myapplication.R
import com.gaurav.myapplication.connection.ConnectionManager
import org.json.JSONException


class HomeFragment : Fragment() {

    lateinit var home_recycler_view: RecyclerView
    lateinit var home_linealayout_manager: LinearLayoutManager
    lateinit var progressbarlayout: RelativeLayout
    lateinit var progress : ProgressBar
    val homedatalist = arrayListOf<HomeData>()
    lateinit var homerecyclerviewAdaptor: HomeRecyclerAdaptor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        home_recycler_view = view.findViewById(R.id.home_recycler_view)
        home_linealayout_manager = LinearLayoutManager(activity)
//        homerecyclerviewAdaptor = HomeRecyclerAdaptor(activity as Context, homedatalist)
//        home_recycler_view.adapter = homerecyclerviewAdaptor
//        home_recycler_view.layoutManager = home_linealayout_manager
        progressbarlayout = view.findViewById(R.id.progressbarr)
        progress= view.findViewById(R.id.progress)


        val homeQuesue = Volley.newRequestQueue(activity as Context)
        val homeUrl = "http://13.235.250.119/v2/restaurants/fetch_result/"
        if (ConnectionManager().checkConnectivity(activity as Context))
        {
            val homejsonrequest = object :
                JsonObjectRequest(
                    Method.GET,
                    homeUrl,
                    null,
                    Response.Listener {


                        try {
                            progressbarlayout.visibility = View.GONE


                            Log.d("xxx", "this is $it")
                            val data1 = it.getJSONObject("data")

                            val success = data1.getBoolean("success")
                            println(" xxxxxxxx $success ")

                            if (success) {
                                val homerecivedata = data1.getJSONArray("data")
                                for (i in 0 until homerecivedata.length()) {
                                    val homejsonobject = homerecivedata.getJSONObject(i)
                                    val homeobject = HomeData(
                                        homejsonobject.getString("id"),
                                        homejsonobject.getString("name"),
                                        homejsonobject.getString("rating"),
                                        homejsonobject.getString("cost_for_one"),
                                        homejsonobject.getString("image_url")

                                    )
                                    homedatalist.add(homeobject)


                                    homerecyclerviewAdaptor =
                                        HomeRecyclerAdaptor(activity as Context, homedatalist)
                                    home_recycler_view.adapter = homerecyclerviewAdaptor
                                    home_recycler_view.layoutManager = home_linealayout_manager

                                }

                            } else {
                                Toast.makeText(activity, "someting Happened wrong", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        } catch (e: JSONException) {
                            Toast.makeText(
                                activity,
                                "Sorry Something happend, Please try again later",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    },
                    Response.ErrorListener {

                    }

                ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val header = HashMap<String, String>()
                    header["Content-type"] = "application/json"
                    header["token"] = "c605abb5fa2d21"
                    return header
                }

            }
            homeQuesue.add(homejsonrequest)

        }else{
            val networdialogbox = AlertDialog.Builder(context)
            networdialogbox.setTitle("Network Error")
            networdialogbox.setMessage("You are not Connected to the network")
            networdialogbox.setPositiveButton("Settings"){ text, listner ->
                val settingintent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                startActivity(settingintent)

                activity?.finish()

            }
            networdialogbox.setNegativeButton("Exit"){ text, listner ->
                ActivityCompat.finishAffinity(activity as Activity)
            }
            networdialogbox.create()
            networdialogbox.show()
        }





        return view


    }

}