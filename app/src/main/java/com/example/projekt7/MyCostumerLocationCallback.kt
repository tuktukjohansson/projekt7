package com.example.projekt7

import android.util.Log
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult

class MyCostumerLocationCallback() : LocationCallback() {

    override fun onLocationResult( locationResult: LocationResult) {
        for (location in locationResult.locations) {
            Log.d("!!", "onLocationResult: lat: ${location.latitude} lng: ${location.longitude}")
        }
    }
}