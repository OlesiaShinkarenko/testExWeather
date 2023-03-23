package com.examle.retrofit

import android.transition.Visibility
import java.util.Date

data class WeatherRes(
    val coord: Coord,
    val weather:List<Weather>,
    val base:String,
    val main: Main,
    val visibility: Long,
    val wind: Wind,
    val rain : Rain,
    val snow:Snow,
    val clouds:Clouds,
    val dt:Long,
    val sys: Sys,
    val timezone:Int,
    val id:Long,
    val name:String,
    val cod:Int
)



data class Weather (
    val id: Int,
    val weather_main: String,
    val weather_description: String,
    val weather_icon:String
        )

data class Main(
    val temp:Double,
    val feels_like:Double,
    val temp_min: Double,
    val temp_max:Double,
    val pressure:Double,
    val humidity: Int,
    val sea_level:Int,
    val grnd_level:Int
)

data class Wind(
    val speed:Double,
    val deg:Int,
    val gust:Double
)

data class Sys(
    val type:Int,
    val id:Int,
    val country:String,
    val sunrise: Long,
    val sunset:Long,
)
data class Coord(
    val lon:Double,
    val lat:Double
)
data class Clouds(
    val all :Int
)

data class Rain(
    val h: Double
)
data class Snow(
    val h:Double
)
