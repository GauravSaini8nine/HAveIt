package com.gaurav.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class Registration :  BaseActivity() {


    lateinit var etName: EditText
    lateinit var etEmail: EditText
    lateinit var etNumber: EditText
    lateinit var etDAddress: EditText
    lateinit var etPassword: EditText
    lateinit var etConfirmPassword: EditText
    lateinit var btnRegister: Button
    lateinit var sharedPreferences: SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        activateToolbar(true)

        sharedPreferences= getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)

        etName = findViewById(R.id.etRegisterName)
        etEmail = findViewById(R.id.etRegisterEmail)
        etNumber = findViewById(R.id.etRegisterNumber)
        etDAddress = findViewById(R.id.etRegisterAddress)
        etPassword = findViewById(R.id.etRegisterPassword)
        etConfirmPassword = findViewById(R.id.etRegisterComfirmPassword)
        btnRegister = findViewById(R.id.btnRegister)






        btnRegister.setOnClickListener {
            var personName = etName.text.toString()
            var personEmail = etEmail.text.toString()
            var personNumber = etNumber.text.toString()
            var personAddress = etDAddress.text.toString()
            var personPassword = etPassword.text.toString()
            var personConfirmPassword = etConfirmPassword.text.toString()
            if (personName.isBlank() || personEmail.isBlank() || personNumber.isBlank() ||
                    personAddress.isBlank() || personPassword.isBlank() || personConfirmPassword.isBlank()) {
                Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show()
            }else{

                if (personPassword != personConfirmPassword) {
                    Toast.makeText(this, "confirm password does not match", Toast.LENGTH_SHORT).show()

                } else {
                    val registerPersonArray: MutableMap<String, String> = mutableMapOf(
                            "Name" to personName,
                            "Email" to personEmail,
                            "Number" to personNumber,
                            "Address" to personAddress,
                            "Password" to personPassword,
                            "ConfirmPassword" to personConfirmPassword)
                    savePreference(
                        registerPersonArray.getValue("Name"),
                        registerPersonArray.getValue("Email"),
                        registerPersonArray.getValue("Number"),
                        registerPersonArray.getValue("Address"),
                        registerPersonArray.getValue("Password") )
                    Log.d("qqqqqqqqqq", "$registerPersonArray")
                    Toast.makeText(this, "Registered, Please Login.", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()

                }

            }



        }



    }
    fun savePreference(rPersonName: String,rPersonEmail: String,rPersonNumber: String,rPersonAddress: String,rPersonPassword: String){
        sharedPreferences.edit().putString("RName", rPersonName).apply()
        sharedPreferences.edit().putString("REmail", rPersonEmail).apply()
        sharedPreferences.edit().putString("RNumber", rPersonNumber).apply()
        sharedPreferences.edit().putString("RAddress", rPersonAddress).apply()
        sharedPreferences.edit().putString("RPassword", rPersonPassword).apply()



    }

}