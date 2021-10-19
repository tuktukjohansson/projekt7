package com.example.projekt7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.projekt7.databinding.ActivityRumapBinding
import com.example.projekt7.user_profile_fragments.PlacesInfoAdapter

class RUMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityRumapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRumapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val adapter = PlacesInfoAdapter(this)
        mMap.setInfoWindowAdapter(adapter)

        createMarkers()
    }

    fun createMarkers() {

        val abCafe = LatLng (59.2995615, 17.9985487)
        val marker = mMap.addMarker(MarkerOptions()
            .position(abCafe)
            .title("A.B.Café")
            .snippet("Roger")
        )

        marker.tag = R.drawable.ic_baseline_stars_24


        val icaSup = LatLng (59.2968838, 17.9830697)
        val marker2 = mMap.addMarker(MarkerOptions()
            .position(icaSup)
            .title("ICA Supermarket")
            .snippet("Anabella")
        )

        marker2.tag = R.drawable.ic_baseline_stars_24


        val pizzeria = LatLng (59.36634, 16.50773)
        val marker3 = mMap.addMarker(MarkerOptions()
            .position(pizzeria)
            .title("Järntorget Pizzeria")
            .snippet("Robert")
        )

        marker3.tag = R.drawable.ic_baseline_stars_24




    }
}