package com.example.projekt7

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.projekt7.databinding.ActivityRumapBinding
import com.example.projekt7.user_profile_fragments.PlaceInfo
import com.example.projekt7.user_profile_fragments.PlacesInfoAdapter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.Marker

class RUMapActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var lastLocation: Location
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var binding: ActivityRumapBinding

    companion object{
        private const val LOCATION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRumapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Marker in Stockholm , maybe CHANGE TO USER LOCATION
        /*val sthlm = LatLng(59.3251172, 18.0710935)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sthlm,15f))*/

        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)
        setUpMap()


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

        val nobelMus = LatLng (59.3251699, 18.0707795)
        val marker4 = mMap.addMarker(MarkerOptions()
            .position(nobelMus)
            .title("Nobel Prize Museum")
            .snippet("Åsa")
        )

        marker4.tag = R.drawable.ic_baseline_stars_24

        val aiRamen = LatLng (59.3334426, 18.05809)
        val marker5 = mMap.addMarker(MarkerOptions()
            .position(aiRamen)
            .title("Ai Ramen Klara")
            .snippet("Rosita")
        )

        marker5.tag = R.drawable.ic_baseline_stars_24

    }

    fun setUpMap(){
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_REQUEST_CODE)

            return
        }
        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener (this) { location ->

            if (location != null){
                lastLocation = location
                val currentLatLong = LatLng(location.latitude, location.longitude)
                placeMarkerOnMap(currentLatLong)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 12f))
            }
        }
    }


    fun placeMarkerOnMap(currentLatLong: LatLng) {
        val markerOptions = MarkerOptions().position(currentLatLong)
        markerOptions.title("My Position")
        mMap.addMarker(markerOptions)



    }

    override fun onMarkerClick(p0: Marker) = false



}