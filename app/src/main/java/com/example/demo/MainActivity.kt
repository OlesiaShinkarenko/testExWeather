package com.example.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment

import com.examle.retrofit.WeatherAPI
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    private val dataModel:DataModel by viewModels ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bNav = findViewById<BottomNavigationView>(R.id.bNav)

        bNav.selectedItemId = R.id.forecast

//        openFrag(ForecastFragment.newInstance())

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