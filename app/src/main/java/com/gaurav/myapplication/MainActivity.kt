package com.gaurav.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity()  {
    lateinit var btnLogin :Button
    lateinit var tvSignUp : TextView
    lateinit var tvForgotPass: TextView
    lateinit var etNumber : EditText
    lateinit var etPassword : EditText
    val validmobileNumber = "1"
    val validpassword = "1"

    lateinit var sharedPreferences: SharedPreferences





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnLogin=  findViewById(R.id.btnLogin)


        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        etNumber= findViewById(R.id.etnumber)
        etPassword = findViewById(R.id.etLoginPassword)
        tvSignUp=  findViewById(R.id.tvSignUp)


        tvSignUp.setOnClickListener {
            val intent= Intent(this, Registration::class.java)
            startActivity(intent)
        }



        tvForgotPass = findViewById(R.id.tvForgetPass)

        tvForgotPass.setOnClickListener {
            val intent= Intent(this, ForgotPassword::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            val loginNumber= etNumber.text.toString()
            val loginPassword = etPassword.text.toString()
            val regNumber = sharedPreferences.getString("RNumber", validmobileNumber)
            val regPassword = sharedPreferences.getString("RPassword", validpassword)
            if(loginNumber== regNumber && loginPassword == regPassword) {
                sharedPreferences.edit().putBoolean("isLogedIn", true).apply()
                startActivity(Intent(this, HomeScreen::class.java))
                finish()

            }else {
                Toast.makeText(this, "Invalid Email or Password\n  Register yourself first" , Toast.LENGTH_SHORT).show()

            }

        }




    }


}