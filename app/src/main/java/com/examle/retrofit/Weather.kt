package com.examle.retrofit

import android.transition.Visibility
import java.util.Date

data class WeatherRes(
    val cod:Int,
    val message:Int,
    val cnt:Int,
    val list: List<ListDt>,
    val city:City

)

data class City(
    val id: Int,
    val name:String,
    val coord: Coord,
    val country:String,
    val timezone:Int,
    val sunrise: Long,
    val sunset:Long
)

data class ListDt(
    val dt:Long,
    val main: Main,
    val weather:List<Weather>,
    val clouds:Clouds,
    val wind: Wind,
    val visibility: Long,
    val pop:Double,
    val sys: Sys,
    val dt_txt: String
)

data class Weather (
    val id: Int,
    val main: String,
    val description: String,
    val icon:String
        )

data class Main(
    val temp:Double,
    val feels_like:Double,
    val temp_min: Double,
    val temp_max:Double,
    val pressure:Double,
    val sea_level:Int,
    val grnd_level:Int,
    val humidity: Int,
    val temp_kf: Double
)

data class Wind(
    val speed:Double,
    val deg:Int,
    val gust:Double
)

data class Sys(
    val pod:Char
)
data class Coord(
    val lat:Double,
    val lon:Double
)
data class Clouds(
    val all :Int
)

