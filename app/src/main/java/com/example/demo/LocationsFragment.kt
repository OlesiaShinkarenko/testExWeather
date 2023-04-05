package com.example.demo

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.AdapterView.OnItemClickListener
import androidx.fragment.app.Fragment
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class LocationsFragment : Fragment() {

    private val dataModel:DataModel by activityViewModels()
    private lateinit var recyclerView: RecyclerView
    private  val recyclerAdapter= RecyclerAdapter()
    var pref:SharedPreferences? = null
    private var launcher: ActivityResultLauncher<Intent>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result:ActivityResult ->
            if (result.resultCode == RESULT_OK){
                    val text = result.data?.getStringExtra("locations")
                recyclerAdapter.addCity(text.toString())
            }
        }
        pref = activity?.getSharedPreferences("Locations", Context.MODE_PRIVATE)
        val jsonText = pref?.getString("loc", null)
        if (!jsonText.isNullOrEmpty()) {
            val gson = GsonBuilder().create()
            val theList = gson.fromJson<ArrayList<String>>(jsonText, object :
                TypeToken<ArrayList<String>>(){}.type)
            for (i in theList)
                recyclerAdapter.addCity(i)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_locations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = recyclerAdapter
       recyclerAdapter.setOnClickListener(object :
       RecyclerAdapter.OnClickListener{
           override fun onClick(position: Int, s: String) {

               saveData(recyclerAdapter.getArray())
               dataModel.message.value = s
               (activity as MainActivity).navController.navigate(R.id.action_locationsFragment_to_forecastFragment)
           }
       })
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.addLocations -> {
                launcher?.launch(Intent(context,AddActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun saveData(result: ArrayList<String>){
        val editor = pref?.edit()
        val gson:Gson = Gson()
        val jsonResult = gson.toJson(result)
        editor?.putString("loc",jsonResult)
        editor?.apply()
    }

    override fun onDestroy() {
        saveData(recyclerAdapter.getArray())
        super.onDestroy()
    }
    companion object {
        @JvmStatic
        fun newInstance() = LocationsFragment()
    }
}