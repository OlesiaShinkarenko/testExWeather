package com.example.demo

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.widget.Toast

import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment


import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private val dataModel:DataModel by viewModels ()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bNav:BottomNavigationView = findViewById (R.id.bNav)


        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        bNav.selectedItemId = R.id.forecast

        openFrag(ForecastFragment.newInstance())

        bNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.locations -> {
                    openFrag(LocationsFragment.newInstance())
                }
                R.id.forecast ->{
                    openFrag(ForecastFragment.newInstance())

                }
                R.id.settings->{
                    Toast.makeText(this,"Settings", Toast.LENGTH_SHORT).show()

                }
            }
            true
        }
        dataModel.message.observe(this,{
        })

//        val tv = findViewById<TextView>(R.id.tv)




    }
    private fun openFrag(f:Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame,f)
            .commit()
    }
}