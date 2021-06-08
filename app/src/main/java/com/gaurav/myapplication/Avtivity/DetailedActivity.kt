package com.gaurav.myapplication.Avtivity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.toolbox.Volley
import com.gaurav.myapplication.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailedActivity : AppCompatActivity() {
    lateinit var detailedname: TextView
    lateinit var detailedImage: ImageView
    lateinit var favbutton: FloatingActionButton
    var restID: String? = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        detailedname = findViewById(R.id.detailed_name)
        detailedImage = findViewById(R.id.detail_image)
        favbutton = findViewById(R.id.fab_detailed)


        if (intent != null) {
            restID = intent.getStringExtra("rest_id")
        } else {
            Toast.makeText(this, "some error occurred", Toast.LENGTH_SHORT).show()
        }
        if (restID == "100") {
            finish()
            Toast.makeText(this, "some error occurred", Toast.LENGTH_SHORT).show()

        }


        val detailedqueue = Volley.newRequestQueue(this)

        val url = "http://13.235.250.119/v2/restaurants/fetch_result/"



    }
}