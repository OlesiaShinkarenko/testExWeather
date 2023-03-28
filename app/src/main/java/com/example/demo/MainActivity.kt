package com.example.demo

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View

import android.widget.Toast

import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController


import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private val dataModel:DataModel by viewModels ()
    lateinit var navController:NavController
    lateinit var  bNav:BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         bNav = findViewById (R.id.bNav)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        navController = findNavController(R.id.fragmentContainerView)
        bNav.setupWithNavController(navController)
        val  appBarConfiguration = AppBarConfiguration(setOf(R.id.locationsFragment,R.id.forecastFragment))

        setupActionBarWithNavController(navController,appBarConfiguration)


        dataModel.message.observe(this,{
        })
//        val tv = findViewById<TextView>(R.id.tv)
    }


//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.toolbar_menu, menu)
//        return true
//    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

}