package com.example.projekt7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ChooseUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_user)

        val businessOwner = findViewById<Button>(R.id.buttonBusinessOwner)
        val regularUser = findViewById<Button>(R.id.buttonRegularUser)

        businessOwner.setOnClickListener {
            //Add function for next activity
        }

        regularUser.setOnClickListener {
            //Add function for next activity
        }

    }
}