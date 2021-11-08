package com.example.projekt7

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projekt7.Model.Place
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.*

//const val EXTRA_USER_MAP = "EXTRA_USER_MAP"
const val EXTRA_MAP_TITLE = "EXTRA_MAP_TITLE"
private const val REQUEST_CODE = 1234

class ProfileScreenActivity : AppCompatActivity() {

    /*    private lateinit var mapAdapter: MapsAdapter
    private lateinit var firestoreDB: FirebaseFirestore
    private lateinit var dbref: DatabaseReference
    private lateinit var rvMaps: RecyclerView
*/
    private lateinit var recyclerView: RecyclerView
    private lateinit var mapsList: ArrayList<Place>
    private lateinit var db: FirebaseFirestore
    private lateinit var profileAdapter: ProfileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_screen)

        val fabCreateMap = findViewById<FloatingActionButton>(R.id.fabCreateButton)
        val fabSignOut = findViewById<FloatingActionButton>(R.id.fabSignOut)
        recyclerView = findViewById(R.id.rvProfileScreen)

        supportActionBar?.hide()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        mapsList = arrayListOf()

        profileAdapter = ProfileAdapter(mapsList)
        recyclerView.adapter = profileAdapter

        spotsListener()

//        userMaps = generateSampleData().toMutableList()


        /*       mapAdapter = MapsAdapter(this, , object : MapsAdapter.OnClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@ProfileScreenActivity, DisplayMapsActivity::class.java)
                intent.putExtra(EXTRA_USER_MAP, userMaps[position])
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
}


/*    private fun getUserData() {

        dbref = FirebaseDatabase.getInstance().getReference("places")
        dbref.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val user = userSnapshot.getValue(Place::class.java)
                        userMaps.add(user!!)
                    }
                    rvMaps.adapter = MapAdapter(userMaps)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

    }
*/


        /*    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val userMap = data?.getSerializableExtra(EXTRA_USER_MAP) as UserMap
            userMaps.add(userMap)
            mapAdapter.notifyItemInserted(userMaps.size - 1)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
*/
/*        private fun showAlertDialog() {
            val mapFormView = LayoutInflater.from(this).inflate(R.layout.dialog_create_map, null)
            val dialog = AlertDialog.Builder(this)
                .setTitle("Map Title")
                .setView(mapFormView)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", null).show()

            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
                val title = mapFormView.findViewById<EditText>(R.id.etTitleS).text.toString()
                if (title.trim().isEmpty()) {
                    Toast.makeText(this, "Map must have non-empty title", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                val intent = Intent(this@ProfileScreenActivity, CreateMapActivity::class.java)
                intent.putExtra(EXTRA_MAP_TITLE, title)
                startActivityForResult(intent, REQUEST_CODE)

                dialog.dismiss()
            }
        }
    }
*/
/*    private fun generateSampleData(): List<Place> {
        return listOf()
    }


}

            UserMap("Memories from University", listOf(Place("Branner Hall", "Best dorm at Stanford", 37.426, -122.163),
                Place("Gates CS building", "Many long nights in this basement", 37.430, -122.173),
                Place("Pinkberry", "First date with my wife", 37.444, -122.170))),
            UserMap(
                "January vacation planning!",
                listOf(
                    Place("Tokyo", "Overnight layover", 35.67, 139.65),
                    Place("Ranchi", "Family visit + wedding!", 23.34, 85.31),
                    Place("Singapore", "Inspired by \"Crazy Rich Asians\"", 1.35, 103.82)
                )
            ),
            UserMap(
                "Singapore travel itinerary",
                listOf(
                    Place("Gardens by the Bay", "Amazing urban nature park", 1.282, 103.864),
                    Place(
                        "Jurong Bird Park",
                        "Family-friendly park with many varieties of birds",
                        1.319,
                        103.706
                    ),
                    Place("Sentosa", "Island resort with panoramic views", 1.249, 103.830),
                    Place(
                        "Botanic Gardens",
                        "One of the world's greatest tropical gardens",
                        1.3138,
                        103.8159
                    )
                )
            ),
            UserMap(
                "My favorite places in the Midwest",
                listOf(
                    Place(
                        "Chicago",
                        "Urban center of the midwest, the \"Windy City\"",
                        41.878,
                        -87.630
                    ),
                    Place("Rochester, Michigan", "The best of Detroit suburbia", 42.681, -83.134),
                    Place(
                        "Mackinaw City",
                        "The entrance into the Upper Peninsula",
                        45.777,
                        -84.727
                    ),
                    Place("Michigan State University", "Home to the Spartans", 42.701, -84.482),
                    Place("University of Michigan", "Home to the Wolverines", 42.278, -83.738)
                )
            ),
            UserMap(
                "Restaurants to try",
                listOf(
                    Place("Champ's Diner", "Retro diner in Brooklyn", 40.709, -73.941),
                    Place("Althea", "Chicago upscale dining with an amazing view", 41.895, -87.625),
                    Place("Shizen", "Elegant sushi in San Francisco", 37.768, -122.422),
                    Place(
                        "Citizen Eatery",
                        "Bright cafe in Austin with a pink rabbit",
                        30.322,
                        -97.739
                    ),
                    Place(
                        "Kati Thai",
                        "Authentic Portland Thai food, served with love",
                        45.505,
                        -122.635
                    )
                )
            )
        )
 */