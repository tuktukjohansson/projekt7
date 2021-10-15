package com.example.projekt7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        val buttonCreate = findViewById<Button>(R.id.buttonCreate)

        buttonLogin.setOnClickListener {
            val userProfile = Intent(this,RUProfileActivity::class.java)
            startActivity(userProfile)
        }

        buttonCreate.setOnClickListener {
            val intent = Intent(this,ChooseUserActivity::class.java)
            startActivity(intent)
        }

    }
}