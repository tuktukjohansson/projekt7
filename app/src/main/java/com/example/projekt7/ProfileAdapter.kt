package com.example.projekt7

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.projekt7.DataManager.db
import com.example.projekt7.Model.Place
import com.example.projekt7.databinding.ActivityProfileScreenBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FieldValue.delete
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


private val placesCollectionRef = Firebase.firestore.collection("places")


data class ProfileAdapter(var spotMaps : ArrayList<Place>, val c: Context) : RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    val TAG = "!!!"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_maps_saved,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val maps : Place = spotMaps[position]
        holder.titles.text = maps.title
        holder.descriptions.text = maps.description
        holder.itemPosition = position


    }

    override fun getItemCount(): Int {
        return spotMaps.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var titles: TextView
        var descriptions : TextView
        var mMenus : ImageView
        var itemPosition = 0

//        val longitude : TextView = itemView.findViewById(R.id.umLongitude)  // COLOCAR OS DOIS NO EXPAND VIEW
//        val latitude : TextView = itemView.findViewById(R.id.umLatitude)
        init {
                titles = itemView.findViewById(R.id.umTitleLocation)
                descriptions = itemView.findViewById(R.id.umDescription)
                mMenus = itemView.findViewById(R.id.mMenus)
                mMenus.setOnClickListener { popupMenus(it) }
        }
    }

    private fun popupMenus(itemView: View) {
        //val itemViewHolder = itemView as ViewHolder
       // Log.d("!!!", "${itemViewHolder.itemPosition} " )
        val position = spotMaps[0]        // VER BEM ISTO nao é zero
        val popupMenus = PopupMenu (c,itemView)   // arranjar esta funcao e ver o que é
        popupMenus.inflate(R.menu.show_menu)
        popupMenus.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.editText ->{
                    val itemView = LayoutInflater.from(c).inflate(R.layout.dialog_create_place,null) // ver se é dialog
                    val titles = itemView.findViewById<EditText>(R.id.etTitle)
                    val descriptions = itemView.findViewById<EditText>(R.id.etDescription)
                                   AlertDialog.Builder(c)
                                       .setView(itemView)
                                       .setPositiveButton("OK") {
                                           dialog,_ ->
                                           position.title = titles.text.toString() // ver se é mesmo o primeiro title correto
                                           position.description= descriptions.text.toString()
                                           notifyDataSetChanged()
                                           Toast.makeText(c,"User Information is Edited", Toast.LENGTH_SHORT).show()
                                           dialog.dismiss()


                                       }
                                       .setNegativeButton("Cancel") {
                                           dialog,_ ->
                                           dialog.dismiss()

                                       }
                                       .create()
                                       .show()
                    true
                }
                R.id.delete -> {
                    AlertDialog.Builder(c)
                        .setTitle("Delete")
                        .setIcon(R.drawable.ic_warning)
                        .setMessage("Are you sure delete this place?")
                        .setPositiveButton("Yes") {
                            dialog,_->
                            spotMaps.removeAt(0) // O Erro está aqui

                            notifyDataSetChanged()
                            Toast.makeText(c,"Delete this place", Toast.LENGTH_SHORT).show()
                            dialog.dismiss()
                            deletePlace()  // funcao para apagar no firebase
                        }
                        .setNegativeButton("No") {
                            dialog,_->
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                    true
                }

                else -> true
            }

        }

        popupMenus.show()
        val popup = PopupMenu::class.java.getDeclaredField("mPopup")
        popup.isAccessible = true
        val menu = popup.get(popupMenus)
        menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
            .invoke(menu,true)
    }

    private fun deletePlace() {
       db.collection("places")
            .document("places")
            .delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }





    }

  /*  private fun updatePlace() {
        db.collection("places")
            .
            .addOnSuccessListener{ Log.d(TAG,"Place successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating")

    }    }
*/






  /*  private fun updatePlace(place: Place, newPlaceMap: Map<String, String>) = CoroutineScope(Dispatchers.IO).launch {
        val placesQuery = placesCollectionRef
            .whereEqualTo("title", place.title)
            .whereEqualTo("description", place.description)

            .get()
            .await()
        if(placesQuery.documents.isNotEmpty()) {
            for(document in placesQuery) {
                try {
                    placesCollectionRef.document(document.id).set(
                        newPlaceMap,
                        SetOptions.merge()
                    ).await()
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@ProfileScreenActivity, e.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        } else {
            withContext(Dispatchers.Main) {
                Toast.makeText(this@ProfileScreenActivity, "No place matched the query.", Toast.LENGTH_LONG).show()
            }
        }
    } */



}