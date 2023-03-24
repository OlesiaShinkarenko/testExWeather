package com.examle.retrofit

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherAPI {
    @GET("data/2.5/forecast?appid=31c42b562a517b89d16aedeb55e891ac&units=metric")
    suspend fun getWeatherById(@Query("q")name:String):WeatherRes
}
