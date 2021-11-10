
package com.example.projekt7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projekt7.Model.Place
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.example.projekt7.databinding.ActivityDisplayMapsBinding

class DisplayMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityDisplayMapsBinding
    private lateinit var userMap: Place

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

//        val bounceBuilder = LatLngBounds.Builder()

        /*for (place in userMap.places) {
            val latLng = LatLng(place.latitude , place.longitude)
            bounceBuilder.include(latLng)
            mMap.addMarker(MarkerOptions().position(latLng).title(place.title).snippet(place.description))
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounceBuilder.build(), 1000,1000,0))
//        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounceBuilder.build(), 1000,1000,0))
    }

         */
    }
}