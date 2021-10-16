package com.example.projekt7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginScreenActivity : AppCompatActivity() {
    val TAG = "!!!"
    var auth = Firebase.auth
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        val buttonCreate = findViewById<Button>(R.id.buttonCreate)
        var editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        var editTextPassword = findViewById<EditText>(R.id.editTextPassword)

        var emailString = editTextEmail.getText().toString()
        var passwordString = editTextPassword.getText().toString()

        buttonLogin.setOnClickListener {
            Log.d(TAG,"login mail: $emailString")
            Log.d(TAG, "login pass: $passwordString")
            /*
            auth.signInWithEmailAndPassword(emailString,passwordString)
                .addOnCompleteListener  { task ->
                    if ( task.isSuccessful) {
                        Log.d(TAG, "loginUser: Success")
                    } else {
                        Log.d(TAG, "loginUser: user not logged in ${task.exception}")
                    }
                }*/
        }

        buttonCreate.setOnClickListener {
            val intent = Intent(this,ChooseUserActivity::class.java)
            startActivity(intent)
        }

    }
}