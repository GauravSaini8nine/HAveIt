package com.gaurav.myapplication.Fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.SharedPreferencesCompat
import com.gaurav.myapplication.R

class ProfileFragment : Fragment() {
//      var profilename :TextView? = null
//    lateinit var profileemail :TextView
//    lateinit var profilecontact :TextView
//    lateinit var profileaddress :TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

//        profilename = view.findViewById(R.id.profilename)
//        profileemail = view.findViewById(R.id.profileEmail)
//        profilecontact = view.findViewById(R.id.profilecontact)
//        profileaddress = view.findViewById(R.id.profileaddress)




        return view
    }

}