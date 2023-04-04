package com.example.demo

import android.graphics.Bitmap
import android.media.session.PlaybackState.CustomAction
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.examle.retrofit.WeatherAPI
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*


class ForecastFragment : Fragment() {

    private val dataModel:DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder().
        baseUrl("https://api.openweathermap.org").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val WeatherAPI = retrofit.create(WeatherAPI::class.java)


        dataModel.message.observe(activity as LifecycleOwner
        ) {
            CoroutineScope(Dispatchers.IO).launch {
                val weather = WeatherAPI.getWeatherById(it)

               activity?.runOnUiThread {
                   view.findViewById<TextView>(R.id.textViewName).text =
                       weather.city.name +" "+ weather.city.country
                   view.findViewById<TextView>(R.id.textViewTemp).text =
                       weather.list[0].main.temp.toInt().toString() +"\u00B0"
                   view.findViewById<TextView>(R.id.textViewFeels).text =
                       "Feels like " + weather.list[0].main.feels_like.toInt().toString()+ "\u00B0"
                      val  apiURL = "https://openweathermap.org/img/wn/"+ weather.list[0].weather[0].icon+"@2x.png"
                   val imageView = view.findViewById<ImageView>(R.id.imageViewWeather)
                   Picasso.with(activity?.applicationContext)
                       .load(apiURL)
                       .into(imageView)
                   view.findViewById<TextView>(R.id.textViewWind).text =
                       weather.list[0].wind.speed.toString()+" m/s winds."
                   view.findViewById<TextView>(R.id.textViewVisility).text =
                       "Visibility "+ (weather.list[0].visibility / 1000).toString() +" kilometers."
                   view.findViewById<TextView>(R.id.textViewPressure).text =
                       "Pressure "+ weather.list[0].main.pressure.toInt().toString() + " hPa."
                   view.findViewById<TextView>(R.id.textViewHumidity).text =
                       "Humidity "+weather.list[0].main.humidity+" %."
                   view.findViewById<TextView>(R.id.textViewTomorrow).text =
                       "Tomorrow will be "+weather.list[1].weather[0].description+"."
                   view.findViewById<TextView>(R.id.textViewTomorrowTemp).text =
                       "Temperature "+weather.list[1].main.temp.toInt().toString()+"\u00B0"+". Feels like "+
                               weather.list[1].main.feels_like.toInt().toString()+ "\u00B0."
                   var res:String =""
                   for (i in 2 until weather.list.size){
                       res +=weather.list[i].dt_txt+ ". Temperature "+ weather.list[i].main.temp.toInt().toString()+"\u00B0"+". Feels like "+
                               weather.list[i].main.feels_like.toInt().toString()+ "\u00B0.\n"
                       Log.d("gf",res)
                   }

                   view.findViewById<TextView>(R.id.textViewWeekTemp).text = res
               }
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            ForecastFragment()
    }
}