package com.example.projekt7.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.projekt7.Model.DataManager
import com.example.projekt7.Model.RegularUser
import com.example.projekt7.R

class CreateRUActivity : AppCompatActivity() {
    lateinit var nameEditText: EditText
    lateinit var mailEditText: EditText
    lateinit var userNameEditText: EditText
    lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_ruactivity)
        val createUserButton = findViewById<Button>(R.id.buttonCreateUser)

        supportActionBar?.hide()

        createUserButton.setOnClickListener {
            nameEditText = findViewById(R.id.businessName)
            mailEditText = findViewById(R.id.editTextMail)
            userNameEditText = findViewById(R.id.editTextUserName)
            passwordEditText = findViewById(R.id.editTextPassword)

            val nameString = nameEditText.text.toString()
            val mailString = mailEditText.text.toString()
            val userNameString = userNameEditText.text.toString()
            val passwordString = passwordEditText.text.toString()

            val newRegularUser = RegularUser(
                nameString,
                mailString,
                userNameString,
                passwordString
            )

            DataManager.regularUserList.add(newRegularUser)
            DataManager.createNewUser(newRegularUser, mailString, passwordString)

            //Nedan är lite check i LogCat på att alla uppgifter sparats
            val printNewUser = DataManager.regularUserList[DataManager.regularUserList.lastIndex]
            Log.d(TAG, "${DataManager.regularUserList.lastIndex}")
            Log.d(TAG, "New user name: ${printNewUser.name}")
            Log.d(TAG, "New user mail: ${printNewUser.mail}")
            Log.d(TAG, "New username: ${printNewUser.userName}")
            Log.d(TAG, "New user password: ${printNewUser.password}")


            if (mailString.isEmpty() || passwordString.isEmpty()) {
                Toast.makeText(
                    applicationContext,
                    "Please fill all the information to create an account",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            Toast.makeText(applicationContext, "Account created !", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginScreenActivity::class.java)
            startActivity(intent)
        }
    }
}

