package com.example.projekt7.user_profile_fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.projekt7.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class PlacesInfoAdapter ( val context: Context) : GoogleMap.InfoWindowAdapter {

    val layoutInflater = LayoutInflater.from(context)

    override fun getInfoWindow(marker: Marker): View? {
        val infoWindow = layoutInflater.inflate(R.layout.info_window_ru,null)

        val imageView = infoWindow.findViewById<ImageView>(R.id.image_star)
        val titleView = infoWindow.findViewById<TextView>(R.id.local_name)
        val userView = infoWindow.findViewById<TextView>(R.id.user_username)

        titleView.text = marker.title
        userView.text = marker.snippet
        imageView.setImageResource(marker.tag as Int)



        return infoWindow
    }

    override fun getInfoContents(marker: Marker): View? {


        return null
    }


}