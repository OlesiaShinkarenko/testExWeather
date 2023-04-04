package com.example.demo

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.activity.result.ActivityResultLauncher
import androidx.core.view.isNotEmpty
import com.examle.retrofit.AutoCompeleteAPI

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder().
        baseUrl("https://api.geoapify.com").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val autoCompeleteAPI = retrofit.create(AutoCompeleteAPI::class.java)

        val searchView = findViewById<SearchView>(R.id.searchcity)

        searchView.isQueryRefinementEnabled = true
        val list = findViewById<ListView>(R.id.listsearch)
        val arrayAdapter = ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line)
        list.adapter = arrayAdapter
        searchView.isIconifiedByDefault = false
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if(!newText.isNullOrEmpty()){
                    CoroutineScope(Dispatchers.IO).launch {
                        val compeleteAPI = newText?.let { autoCompeleteAPI.getAtoCom(it) }
                        runOnUiThread{
                            if (compeleteAPI!!.results != null) {
                                arrayAdapter.clear()
                                for(i in compeleteAPI.results)
                                    arrayAdapter.add(i.formatted)
                            }
                        }
                    }
                }
                  else{
                      arrayAdapter.clear()
                }
                return true
            }
        })

        val btnBack = findViewById<ImageButton>(R.id.btnback)
        btnBack.setOnClickListener{
            finish()
        }
        list.onItemClickListener =  object : OnItemClickListener{
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val itemValue = list.getItemAtPosition(position) as String
                val i = Intent()
                i.putExtra("locations", itemValue)
                setResult(RESULT_OK, i)
                finish()
            }

        }
    }

}