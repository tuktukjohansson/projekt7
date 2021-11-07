package com.example.projekt7

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projekt7.Model.UserMap


data class MapsAdapter (val context : Context, val userMaps : List<UserMap>, val onClickListener: OnClickListener) : RecyclerView.Adapter<MapsAdapter.ViewHolder>() {

    interface OnClickListener {
        fun onItemClick(position: Int)
    }

    //(R.layout.simple_list_item_1) has been deleted and exchanged
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_saved_spots, parent, false)
//        val view = LayoutInflater.from(context).inflate(R.id.sav, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userMaps[position])
//        val userMap = userMaps[position]
        holder.itemView.setOnClickListener{
            onClickListener.onItemClick(position)
        }
//        val textViewTitle = holder.itemView.findViewById<TextView>(android.R.id.text1)
//        textViewTitle.text = userMap.title
    }

    override fun getItemCount() = userMaps.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(userMap: UserMap) {
        }

    }
}