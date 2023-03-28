package com.example.demo

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner

class LocationsFragment : Fragment() {

    private val dataModel:DataModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_locations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

                view.findViewById<Button>(R.id.buttonSendLoc).setOnClickListener{
            dataModel.message.value = "London"
            (activity as MainActivity).navController.navigate(R.id.action_locationsFragment_to_forecastFragment)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);

        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.addLocations -> {
             val intent = Intent(activity?.applicationContext,AddActivity::class.java )
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    companion object {
        @JvmStatic
        fun newInstance() = LocationsFragment()
    }



}