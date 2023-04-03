package com.examle.retrofit

import android.webkit.JsPromptResult
import org.json.JSONObject

data class CitiesAndCounties(
    val results: Array<InfoCitCoun>
)

    data class InfoCitCoun (
        val datasource: JSONObject,
        val name: String,
        val country: String,
        val country_code: String,
        val state: String?,
        val county: String,
        val city: String,
        val municipality: String?,
        val postcode: String?,
        val village:String?,
        val log: Double,
        val lat: Double,
        val formatted: String,
        val address_line1:String,
        val address_line2: String,
        val countryPair: String?,
        val category: String,
        val timezone: JSONObject,
        val result_type: String,
        val rank:JSONObject,
        val region: String,
        val place_id: String,
        val bbox:JSONObject,
        val state_code: String?,
        val state_COG: String?,
        val department_COG: String?,
        )



