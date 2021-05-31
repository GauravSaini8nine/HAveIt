package com.gaurav.myapplication.Avtivity

import android.os.Bundle
import com.gaurav.myapplication.R

class ForgotPassword : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        activateToolbar(true)
    }
}