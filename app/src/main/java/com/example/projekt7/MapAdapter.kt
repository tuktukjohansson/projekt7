package com.example.projekt7

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projekt7.Model.Place
import org.w3c.dom.Text

class MapAdapter (var spotMaps : MutableList<Place>) : RecyclerView.Adapter<MapAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_maps_saved,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = spotMaps[position]

        holder.mapTitle.text = currentItem.title
        holder.mapDescription.text = currentItem.description
        holder.mapLongitude.text = currentItem.longitude.toString()
        holder.mapLatitude.text = currentItem.latitude.toString()
    }

    override fun getItemCount(): Int {
        return  spotMaps.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val mapTitle : TextView = itemView.findViewById(R.id.umTitleLocation)
        val mapDescription : TextView = itemView.findViewById(R.id.umDescription)
        val mapLongitude : TextView = itemView.findViewById(R.id.umLongitude)
        val mapLatitude : TextView = itemView.findViewById(R.id.umLatitude)

    }


}