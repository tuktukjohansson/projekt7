package com.example.projekt7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projekt7.Model.Place
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

val EXTRA_USER_MAP = "EXTRA_USER_MAP"
const val EXTRA_MAP_TITLE = "EXTRA_MAP_TITLE"
private const val REQUEST_CODE = 1234

class ProfileScreenActivity : AppCompatActivity() {

    /*    private lateinit var mapAdapter: MapsAdapter
    private lateinit var rvMaps: RecyclerView
*/
    private lateinit var recyclerView: RecyclerView
    private lateinit var mapsList: ArrayList<Place>
    private lateinit var db: FirebaseFirestore
    private lateinit var profileAdapter: ProfileAdapter
    private val placesCollectionRef = Firebase.firestore.collection("places")
    private lateinit var etTitle : EditText
    private lateinit var etDescription :EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_screen)

        val fabCreateMap = findViewById<FloatingActionButton>(R.id.fabCreateButton)
        val fabSignOut = findViewById<FloatingActionButton>(R.id.fabSignOut)
        recyclerView = findViewById(R.id.rvProfileScreen)

        supportActionBar?.hide()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        mapsList = arrayListOf() // Troquei ver se nao da problema antes estava arrayOf()

        profileAdapter = ProfileAdapter(mapsList,this)
        recyclerView.adapter = profileAdapter

        spotsListener()



/*               mapsAdapter = MapsAdapter(this, object : MapsAdapter.OnClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@ProfileScreenActivity, DisplayMapsActivity::class.java)
                intent.putExtra(EXTRA_USER_MAP, mapsList[position])
                startActivity(intent)
            }
        })
*/



        fabCreateMap.setOnClickListener {
            startActivity(Intent(this, CreateMapActivity::class.java))
        }

        fabSignOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, LoginScreenActivity::class.java))
            Toast.makeText(this, "Signed out!", Toast.LENGTH_SHORT).show()
            finish()
        }

    }

    private fun spotsListener() {
        db = FirebaseFirestore.getInstance()
        db.collection("places")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if (error != null) {
                        Log.e("Firestore Error", error.message.toString())
                        return
                    }
                    for (dc: DocumentChange in value?.documentChanges!!) {
                        if (dc.type == DocumentChange.Type.ADDED) {
                            mapsList.add(dc.document.toObject(Place::class.java))
                        }
                    }
                    profileAdapter.notifyDataSetChanged()
                }
            })
    }

    // funcoes para o Adaptar DATA
/*
    private fun getOldPlace(): Place {
        val title = etTitle.text.toString()
        val description = etDescription.text.toString()
        return Place(title, description)
    }
*/
  /*  private fun getNewPlaceMap(): Map<String, Any> {
        val title = etTitle.text.toString()
        val description = etDescription.text.toString()

        val map = mutableMapOf<String, Any>()
        if(title.isNotEmpty()) {
            map["title"] = title
        }
        if(description.isNotEmpty()) {
            map["description"] = description
        }
        return map
    } */

   /* private fun subscribeToRealtimeUpdates() {
        placesCollectionRef.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            firebaseFirestoreException?.let {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                return@addSnapshotListener
            }
            querySnapshot?.let {
                val sb = StringBuilder()
                for(document in it) {
                    val place = document.toObject<Place>()
                    sb.append("$place\n")
                }

            }
        }
    } */










}
