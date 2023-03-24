package com.example.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.examle.retrofit.GEOAPI
import com.examle.retrofit.WeatherAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tv = findViewById<TextView>(R.id.tv)


        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder().
        baseUrl("https://api.openweathermap.org").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val WeatherAPI = retrofit.create(WeatherAPI::class.java)


            CoroutineScope(Dispatchers.IO).launch {
                val weather = WeatherAPI.getWeatherById("London")
                runOnUiThread{
                    tv.text = weather.city.sunrise.toString()
                }
            }

    }
}