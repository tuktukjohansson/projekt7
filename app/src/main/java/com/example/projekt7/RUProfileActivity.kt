package com.example.projekt7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.projekt7.user_profile_fragments.HomeFragment
import com.example.projekt7.user_profile_fragments.SavedSpotsFragment
import com.example.projekt7.user_profile_fragments.SharedSpotsFragment
import com.google.android.material.navigation.NavigationView

class RUProfileActivity : AppCompatActivity() {

    lateinit var toggle : ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ruprofile)

        val homeFragment = HomeFragment()
        val savedSpotsFragment = SavedSpotsFragment()
        val sharedSpotsFragment = SharedSpotsFragment()

        drawerLayout = findViewById(R.id.drawer_layout)
        val navView : NavigationView = findViewById(R.id.navigation_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {

            it.isChecked = true

            when(it.itemId){

                R.id.nav_home -> replaceFragment(HomeFragment(), it.title.toString())
                R.id.saved_spots -> replaceFragment(SavedSpotsFragment(), it.title.toString())
                R.id.share_spots -> replaceFragment(SharedSpotsFragment(), it.title.toString())
                R.id.explore -> Toast.makeText(applicationContext,"Function not available yet",Toast.LENGTH_SHORT).show()
            }
                true
        }
    }

    private fun replaceFragment(fragment: Fragment,title : String){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.flProfileFragment,fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}