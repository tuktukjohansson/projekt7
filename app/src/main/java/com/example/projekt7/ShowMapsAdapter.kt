package com.example.projekt7

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import com.example.projekt7.Model.Place
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class ShowMapsAdapter(val context: Context) : GoogleMap.InfoWindowAdapter {

    val layoutInflater = LayoutInflater.from(context)

    override fun getInfoWindow(marker: Marker): View? {
        val infoWindow = layoutInflater.inflate(R.layout.snippet_dialog, null)

        val titleView = infoWindow.findViewById<EditText>(R.id.snippTitle)
        val descriptView = infoWindow.findViewById<EditText>(R.id.snippDescription)

        val place = marker.tag as Place

        titleView.setText(place.title)
        descriptView.setText(place.description)

        return infoWindow
    }

    override fun getInfoContents(p0: Marker): View? {
        return null
    }


}