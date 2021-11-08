package com.example.projekt7

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projekt7.Model.Place

class ProfileAdapter(var spotMaps : ArrayList<Place>) : RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_maps_saved,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val maps : Place = spotMaps[position]
        holder.titles.text = maps.title
        holder.descriptions.text = maps.description
        holder.longitude.text = maps.longitude.toString()
        holder.latitude.text = maps.latitude.toString()

    }

    override fun getItemCount(): Int {
        return spotMaps.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val titles : TextView = itemView.findViewById(R.id.umTitleLocation)
        val descriptions : TextView = itemView.findViewById(R.id.umDescription)
        val longitude : TextView = itemView.findViewById(R.id.umLongitude)
        val latitude : TextView = itemView.findViewById(R.id.umLatitude)
    }

}