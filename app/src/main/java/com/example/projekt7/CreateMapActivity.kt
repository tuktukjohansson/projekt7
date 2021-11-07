package com.example.projekt7

import android.app.Activity
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.projekt7.Model.Place
import com.example.projekt7.Model.UserMap
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.projekt7.databinding.ActivityCreateMapBinding
import com.google.android.gms.maps.model.Marker
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.*

class CreateMapActivity : AppCompatActivity(), OnMapReadyCallback {

    lateinit var ImageUri: Uri
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityCreateMapBinding
    private var markers: MutableList<Marker> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle("Choose your favourite spots!")

        // Code is to show the map-collection the user has typed on actionbar
        //= intent.getStringExtra(EXTRA_MAP_TITLE)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        mapFragment.view?.let {
            Snackbar.make(it, "Long press to add a marker!", Snackbar.LENGTH_INDEFINITE)
                .setAction("Got it!", {})
                .setActionTextColor(ContextCompat.getColor(this, android.R.color.white))
                .show()
        }
    }

    //val fbImage = findViewById<ImageView>(R.id.fbImage)

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_create_map, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.miSave) {
            if (markers.isEmpty()) {
                Toast.makeText(this, "There must be at least one marker", Toast.LENGTH_LONG).show()
                return true
            }
            val places = markers.map { marker ->
                Place(
                    marker.title,
                    marker.snippet,
                    marker.position.latitude,
                    marker.position.longitude
                )
            }
/*            val userMap = UserMap(intent.getStringExtra(EXTRA_MAP_TITLE), places)
            val data = Intent()
            data.putExtra(EXTRA_USER_MAP, userMap)
            setResult(Activity.RESULT_OK, data)
            finish()
*/            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.setOnInfoWindowClickListener { markerToDelete ->
            markers.remove(markerToDelete)
            markerToDelete.remove()
        }

        //Click on map to add a marker. When marker adds to the map, a function initiate
        mMap.setOnMapLongClickListener { latLng ->
            //startActivity(Intent(this, UploadImageActivity::class.java))
            //Start a new activity for adding title, description, username adds automatic
            // and upload image. By pressing button "Save", everything will be stored to
            //Firestore
            showAlertDialog(latLng)

        }
        val stockholm = LatLng(59.329308, 18.068596)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(stockholm, 10f))
    }


    //This function is only for an alert dialog to show
    private fun showAlertDialog(latLng: LatLng) {
        val placeFormView = LayoutInflater.from(this).inflate(R.layout.dialog_create_place, null)
        val dialog = AlertDialog.Builder(this)
            .setTitle("Save a spot")
            .setView(placeFormView)
            .setNeutralButton("Select Image") { dialog, which ->
//                selectImage()
            }
            .setNegativeButton("Cancel", null)
            .setPositiveButton("OK", null).show()

        //When pressing "OK" button on alertdialog, this function initiates.
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
            val title = placeFormView.findViewById<EditText>(R.id.etTitle).text.toString()
            val description =
                placeFormView.findViewById<EditText>(R.id.etDescription).text.toString()
            //If title or description is empty, a toast is returned
            if (title.trim().isEmpty() || description.trim().isEmpty()) {
                Toast.makeText(
                    this,
                    "Place must have non-empty title and description",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            //If title and description is not empty, the marker adds position, title, description
            val marker =
                mMap.addMarker(MarkerOptions().position(latLng).title(title).snippet(description))
            //Needs another variable for upload image
            // val imageUp = ****
            //if (marker && imageUp != null)
            if (marker != null) {
                markers.add(marker)
                // uploadImage()
                val place = Place(title, description, latLng.latitude, latLng.longitude,)
                DataManager.db.collection("places").add(place)

            }
            //Alert dialog turns down
            dialog.dismiss()
        }
    }
}

/*    private fun uploadImage() {
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Uploading File...")
        progressDialog.setCancelable(false)
        progressDialog.show()
        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)
        val storageReference = FirebaseStorage.getInstance().getReference("image/$fileName")

        storageReference.putFile(ImageUri).addOnSuccessListener {
            fbImage.setImageURI(null)
            Toast.makeText(this@CreateMapActivity, "Sucessfuly uploaded", Toast.LENGTH_SHORT).show()
            if (progressDialog.isShowing) progressDialog.dismiss()

        }.addOnFailureListener{
            if (progressDialog.isShowing) progressDialog.dismiss()
            Toast.makeText(this@CreateMapActivity, "Failed to upload", Toast.LENGTH_SHORT).show()
        }
    }*/

/*    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 100)
    }

 */
/*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == RESULT_OK ) {

            ImageUri = data?.data!!
            fbImage.setImageURI(ImageUri)

        }
    }
 */

}
}
 */