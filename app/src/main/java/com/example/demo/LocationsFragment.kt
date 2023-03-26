package com.example.demo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner

class LocationsFragment : Fragment() {

    private val dataModel:DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_locations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.buttonSendLoc).setOnClickListener{
            dataModel.message.value = "London"
            (activity as AppCompatActivity).supportActionBar?.title = "London"
            (activity as AppCompatActivity).supportFragmentManager.beginTransaction().replace(R.id.frame,ForecastFragment.newInstance()).commit()
        }
        (activity as AppCompatActivity).supportActionBar?.title = resources.getString(R.string.locations)
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        @JvmStatic
        fun newInstance() = LocationsFragment()
    }



}