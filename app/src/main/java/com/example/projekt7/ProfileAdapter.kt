package com.example.projekt7

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.projekt7.Model.Place

data class ProfileAdapter(var spotMaps : ArrayList<Place>, val c: Context) : RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_maps_saved,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val maps : Place = spotMaps[position]
        holder.titles.text = maps.title
        holder.descriptions.text = maps.description


    }

    override fun getItemCount(): Int {
        return spotMaps.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var titles: TextView
        var descriptions : TextView
        var mMenus : ImageView

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
        val position = spotMaps[1]          // VER BEM ISTO nao é zero
        val popupMenus = PopupMenu (c,itemView)
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
                        }
                        .setNegativeButton("No") {
                            dialog,_->
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                    true
                }
                R.id.share -> {
                    val itemViewShare = LayoutInflater.from(c).inflate(R.layout.dialog_share_place,null)
                    AlertDialog.Builder(c)
                        .setTitle("Share")
                        .setIcon(R.drawable.ic_share)
                        .setMessage("Do you want to share this spot with othres?")
                        .setPositiveButton("Yes") {
                            dialog,_->
                            //spotMaps.[] // depois de carregar para aonde me leva
                            notifyDataSetChanged()
                            Toast.makeText(c,"You are sharing this place",Toast.LENGTH_SHORT).show()
                            dialog.dismiss()
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

}