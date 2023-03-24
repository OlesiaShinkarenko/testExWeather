package com.examle.retrofit

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherAPI {
    @GET("data/2.5/forecast?appid=31c42b562a517b89d16aedeb55e891ac")
    suspend fun getWeatherById(@Query("q")name:String):WeatherRes
}

interface GEOAPI{
    @GET("geo/1.0/direct?limit=1&appid=31c42b562a517b89d16aedeb55e891ac")
suspend fun getLonLat(@Query("q")name:String):List<Geocoding>
}