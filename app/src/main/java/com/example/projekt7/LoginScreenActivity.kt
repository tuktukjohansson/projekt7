package com.example.projekt7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginScreenActivity : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth
    lateinit var textEmail : EditText
    lateinit var textPassword : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        firebaseAuth = FirebaseAuth.getInstance()

        textEmail = findViewById(R.id.editTextEmail)
        textPassword = findViewById(R.id.editTextPassword)

        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        val buttonCreate = findViewById<Button>(R.id.buttonCreate)
        var editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        var editTextPassword = findViewById<EditText>(R.id.editTextPassword)

        var emailString = editTextEmail.getText().toString()
        var passwordString = editTextPassword.getText().toString()

        val firebaseUser = firebaseAuth.currentUser

        buttonLogin.setOnClickListener {
            loginUser()
            /*val userProfile = Intent(this,RUProfileActivity::class.java)
            startActivity(userProfile)*/
            }

        buttonCreate.setOnClickListener {
            val intent = Intent(this,ChooseUserActivity::class.java)
            startActivity(intent)
        }

    }

    fun loginUser() {
        val email = textEmail.text.toString()
        val password = textPassword.text.toString()
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(applicationContext,"Enter an email and password", Toast.LENGTH_SHORT).show()
            return
        }
        DataManager.auth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener { task ->
             if (task != null)
                 gotoActivity()
            }

    }

    fun gotoActivity(){
        val userProfile = Intent(this,RUProfileActivity::class.java)
        startActivity(userProfile)
    }

}