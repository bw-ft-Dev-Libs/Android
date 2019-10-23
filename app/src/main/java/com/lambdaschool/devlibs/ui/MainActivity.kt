package com.lambdaschool.devlibs.ui

import android.content.Intent
import android.os.Bundle

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lambdaschool.devlibs.R

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.lambdaschool.devlibs.ui.ui.home.HomeFragment

class MainActivity : AppCompatActivity(), HomeFragment.OnHomeFragmentInteractionListener {

    enum class MainActivityFragmentActionKeys {
        LOG_OUT
    }

    override fun onHomeFragmentInteractionListener(enumKey: MainActivityFragmentActionKeys) {
        when (enumKey) {
            MainActivityFragmentActionKeys.LOG_OUT -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }

    /*
    * Main activity should
    * 1. set up nav bar.
    *
    * 2 ???????
    *
    *
    *
    *
    *
    * */


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView = findViewById<BottomNavigationView>(R.id.nav_view)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_home, R.id.navigation_create, R.id.navigation_list
        )
            .build()
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(navView, navController)
    }

}
