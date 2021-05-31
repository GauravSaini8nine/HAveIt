package com.gaurav.myapplication.Avtivity

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.gaurav.myapplication.R

open class BaseActivity : AppCompatActivity(){




    internal fun activateToolbar(enableHome: Boolean) {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(enableHome)
    }


    internal fun activateHamburger(enableHome: Boolean) {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(enableHome)

    }
}