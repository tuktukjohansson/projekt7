package com.example.projekt7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.projekt7.profile_fragments.SavedSpotsFragment
import com.example.projekt7.profile_fragments.SharedSpotsFragment

class UserProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val savedFragment = SavedSpotsFragment()
        val sharedFragment = SharedSpotsFragment()

        val btnSavedSpots = findViewById<Button>(R.id.btnFragment1)
        val btnSharedSpots = findViewById<Button>(R.id.btnFragment2)
        val btnExplore = findViewById<Button>(R.id.btnFragment3)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragments, savedFragment)
            commit()
        }

        btnSavedSpots.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragments, savedFragment)
                commit()
        }
    }

        btnSharedSpots.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragments, sharedFragment)
                commit()
            }
        }

        btnExplore.setOnClickListener {
            exploreActivity()
        }

    }

    private fun exploreActivity() {
        val intent = Intent (this, RUMapsActivity::class.java)
        startActivity(intent)
    }
}