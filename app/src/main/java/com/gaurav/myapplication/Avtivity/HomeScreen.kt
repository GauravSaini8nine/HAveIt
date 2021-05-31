package com.gaurav.myapplication.Avtivity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.gaurav.myapplication.Fragments.AboutApp
import com.gaurav.myapplication.Fragments.FavoriteFragment
import com.gaurav.myapplication.Fragments.ProfileFragment
import com.gaurav.myapplication.Fragments.HomeFragment
import com.gaurav.myapplication.R
import com.google.android.material.navigation.NavigationView

class HomeScreen : BaseActivity() {

    lateinit var drawerLayout : DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView
    var previousMenuItem : MenuItem? = null





    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        activateToolbar(true)




        drawerLayout= findViewById(R.id.homeScreen)
        coordinatorLayout = findViewById(R.id.coordinator)
        frameLayout = findViewById(R.id.frame)
        navigationView = findViewById(R.id.navigation)
        homeDashboard()

        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this,drawerLayout,
            R.string.open_drawer,
            R.string.Close_drawer
        )

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()


        navigationView.setNavigationItemSelectedListener {
            if (previousMenuItem!= null){
                previousMenuItem?.isChecked = false
            }

            it.isCheckable = true
            it.isChecked = true
            previousMenuItem= it

            when(it.itemId){
                R.id.homedashboard -> {
                    homeDashboard()
                }
                R.id.favorite ->  {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, FavoriteFragment())
                        .commit()
                    supportActionBar?.title = "Favorites"
                    drawerLayout.closeDrawers()
                }
                R.id.profile ->  {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, ProfileFragment())
                        .commit()
                    supportActionBar?.title = "Profile"
                    drawerLayout.closeDrawers()
                }
                R.id.aboutApp ->  {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, AboutApp())
                        .commit()
                    supportActionBar?.title = "About App"
                    drawerLayout.closeDrawers()
                }
            }

            return@setNavigationItemSelectedListener true
        }


//        logoutButton = findViewById(R.id.logoutButton)
//
//
//        logoutButton.setOnClickListener {
//            sharedPreferences.edit().putBoolean("isLogedIn", false).apply()
//            startActivity(Intent(this,MainActivity::class.java))
//            finish()
//        }



    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        val id= item.itemId
        if (id== android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)

    }
    fun homeDashboard(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, HomeFragment())
            .commit()

        supportActionBar?.title = "Home"
        navigationView.setCheckedItem(R.id.homedashboard)
        drawerLayout.closeDrawers()


    }

    override fun onBackPressed() {

        val frag = supportFragmentManager.findFragmentById(R.id.frame)
        when(frag) {
            !is HomeFragment -> homeDashboard()

            else -> {super.onBackPressed()}
        }

    }

}