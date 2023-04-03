package com.examle.retrofit

import org.json.JSONObject
import retrofit2.http.GET
import retrofit2.http.Query

interface AutoCompeleteAPI {
    @GET("v1/geocode/autocomplete?format=json&apiKey=7c3f4705a1264ce3bbf6ac019349381a")
    suspend fun getAtoCom(@Query("text")name:String):CitiesAndCounties
}