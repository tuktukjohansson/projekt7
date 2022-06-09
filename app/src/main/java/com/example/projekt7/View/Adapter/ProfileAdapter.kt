package com.example.projekt7.View.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.projekt7.Model.Place
import com.example.projekt7.R

data class ProfileAdapter(var spotMaps: ArrayList<Place>, val onClickListener: OnClickListener) :
    RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    interface OnClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.usermaps_saved, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val maps: Place = spotMaps[position]
        holder.titles.text = maps.title
        holder.descriptions.text = maps.description

        holder.itemView.setOnClickListener {
            onClickListener.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return spotMaps.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titles: TextView = itemView.findViewById(R.id.umTitleLocation)
        var descriptions: TextView = itemView.findViewById(R.id.umDescription)

        init {
            titles = itemView.findViewById(R.id.umTitleLocation)
            descriptions = itemView.findViewById(R.id.umDescription)
        }
    }
}