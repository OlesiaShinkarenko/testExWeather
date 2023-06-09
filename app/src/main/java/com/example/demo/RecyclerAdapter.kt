package com.example.demo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter :RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){


    val CitiesList = ArrayList<String>()
    private var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycle_layout,parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
       return CitiesList.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(CitiesList[position])
        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, CitiesList[position] )
            }
        }
        holder.itemView.findViewById<ImageButton>(R.id.delete_list_item).setOnClickListener {
            CitiesList.removeAt(position)
            notifyDataSetChanged()
        }
    }
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int, s: String)
    }
     class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
          val  itemTitle:TextView = itemView.findViewById(R.id.description_location)

         fun bind(city:String){
             itemTitle.text = city
         }

    }

    fun addCity(city: String){
        CitiesList.add(city)
        notifyDataSetChanged()
    }

    fun getArray(): ArrayList<String> {
        return CitiesList
    }
}