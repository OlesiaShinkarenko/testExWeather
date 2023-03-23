package com.examle.retrofit

data class Geocoding (
    val name:String,
    val local_names:Local,
    val lat: Double,
    val lon: Double,
    val country: String
        )
data class Local(
    val local_names: String
)