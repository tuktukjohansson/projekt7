package com.example.projekt7.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.projekt7.Model.BusinessOwner
import com.example.projekt7.Model.DataManager
import com.example.projekt7.R

class CreateBOActivity : AppCompatActivity() {
    lateinit var nameEditText: EditText
    lateinit var businessEditText: EditText
    lateinit var streetEditText: EditText
    lateinit var zipEditText: EditText
    lateinit var phoneEditText: EditText
    lateinit var mailEditText: EditText
    lateinit var keyEditText: EditText
    lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_boactivity)
        val createUserButton = findViewById<Button>(R.id.buttonCreateUser)

        supportActionBar?.hide()

        createUserButton.setOnClickListener() {
            nameEditText = findViewById(R.id.businessName)
            businessEditText = findViewById(R.id.adressBusiness)
            zipEditText = findViewById(R.id.phoneNumber)
            mailEditText = findViewById(R.id.editTextMail)
            passwordEditText = findViewById(R.id.editTextPassword)
            keyEditText = findViewById(R.id.editTextIdentityKey)

            val nameString = nameEditText.getText().toString()
            val streetString = streetEditText.getText().toString()
            val businessString = businessEditText.getText().toString()
            val passwordString = passwordEditText.getText().toString()
            val zipString = zipEditText.getText().toString()
            val phoneString = phoneEditText.getText().toString()
            val mailString = mailEditText.getText().toString()

            val newBusinessOwner = BusinessOwner(
                nameString,
                businessString,
                streetString,
                zipString,
                phoneString,
                mailString,
                passwordString
            )

            DataManager.businessOwnerList.add(newBusinessOwner)

            //Nedan är lite check i LogCat på att alla uppgifter sparats
            val printNewUser = DataManager.businessOwnerList[DataManager.businessOwnerList.lastIndex]
            Log.d(TAG, "${DataManager.businessOwnerList.lastIndex}")
            Log.d(TAG, "New user name: ${printNewUser.name}")
            Log.d(TAG, "New user business: ${printNewUser.business}")
            Log.d(TAG, "New user street: ${printNewUser.adressStreet}")
            Log.d(TAG, "New user zipcode: ${printNewUser.townZipCode}")
            Log.d(TAG, "New user phone: ${printNewUser.phone}")
            Log.d(TAG, "New user mail: ${printNewUser.mail}")
            Log.d(TAG, "New user password: ${printNewUser.password}")

            DataManager.createBO(mailString, passwordString)

            val intent = Intent(this, LoginScreenActivity::class.java)
            startActivity(intent)
            Toast.makeText(applicationContext, "Account created !", Toast.LENGTH_SHORT).show()
        }
    }
}