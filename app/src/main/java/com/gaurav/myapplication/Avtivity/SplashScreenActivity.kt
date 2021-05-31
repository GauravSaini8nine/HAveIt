package com.gaurav.myapplication.Avtivity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.gaurav.myapplication.R

@Suppress("DEPRECATION")
class SplashScreenActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)


        val isLogedIn = sharedPreferences.getBoolean("isLogedIn", false)



        Handler().postDelayed({
            if (isLogedIn){
                startActivity(Intent(this, HomeScreen::class.java))


            }else{
                val startAct = Intent(this, MainActivity::class.java)
                startActivity(startAct)
                }
            finish()

        },2000)
    }


}