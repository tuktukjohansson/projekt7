package com.example.projekt7

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projekt7.Model.UserMap


data class MapsAdapter (val context : Context, val userMaps : List<UserMap>, val onClickListener: OnClickListener) : RecyclerView.Adapter<MapsAdapter.ViewHolder>() {

    interface OnClickListener {
        fun onItemClick(position: Int)
    }

    //(R.layout.simple_list_item_1) has been deleted and exchanged
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(context).inflate(R.layout.item_saved_spots, parent, false)
          val view = LayoutInflater.from(context).inflate(R.layout.simple_list_item_1), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
          val userMap = userMaps[position]
        holder.itemView.setOnClickListener{
            onClickListener.onItemClick(position)
        }

        val textViewTitle = holder.itemView.findViewById<TextView>(android.R.id.text1)
        textViewTitle.text = userMap.title
    }

    override fun getItemCount() = userMaps.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
/*        val tvUsername : TextView = itemView.findViewById(R.id.tvUsername)
        val tvDescription : TextView = itemView.findViewById(R.id.tvDescription)
        val tvImage : ImageView = itemView.findViewById(R.id.ivImage)
*/
    }
}