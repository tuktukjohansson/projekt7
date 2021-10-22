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
import com.example.projekt7.user_profile_fragments.PlacesInfoAdapter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.protobuf.DescriptorProtos
import java.util.*

class RUMapActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityRumapBinding
    private lateinit var lastLocation: Location
    private lateinit var fusedLocationClient : FusedLocationProviderClient
    
    companion object {
        
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
        
        mMap.uiSettings.isZoomControlsEnabled = true 

        val adapter = PlacesInfoAdapter(this)
        mMap.setInfoWindowAdapter(adapter)
        
        mMap.setOnMarkerClickListener(this) 

        setUpMap()
        createMarkers()
        setMapLongClick(mMap)
        
        
    }
    
    fun setUpMap() {
        
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission (this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_REQUEST_CODE)
           
            return 
        }
       
        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) {location ->

            if (location != null) {
                lastLocation = location
                val currentLatLong = LatLng(location.latitude, location.longitude)
                placeMarkerOnMap(currentLatLong)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 12f))

            }


        }
    
    
    }
    
    private fun placeMarkerOnMap (currentLatLong: LatLng) {
        
        val markerOptions = MarkerOptions().position(currentLatLong)
        markerOptions.title("$currentLatLong")
        mMap.addMarker(markerOptions)
    
    }
    
    override fun onMarkerClick(p0: Marker?) = false
        
    

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
    
    private fun setMapLongClick ( map: GoogleMap) {
        map.setOnMapLongClickListener { latLng ->
            val snippet = String.format(
                Locale.getDefault(),
                "Lat: %1$.5f, Long: %2$.5f",
                latLng.latitude,
                latLng.longitude
            )
            map.addMarker(
                MarkerOptions()
                 .position(latLng)
                 .title("Insert this place")
                 .snippet("Click here")
                 .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                 )
        
        }
    }

    
}
