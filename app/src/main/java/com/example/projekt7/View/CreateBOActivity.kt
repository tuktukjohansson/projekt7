package com.example.projekt7.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.projekt7.Model.BusinessUser
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

        createUserButton.setOnClickListener {
            nameEditText = findViewById(R.id.businessName)
            businessEditText = findViewById(R.id.adressBusiness)
            zipEditText = findViewById(R.id.phoneNumber)
            mailEditText = findViewById(R.id.editTextMail)
            passwordEditText = findViewById(R.id.editTextPassword)
            keyEditText = findViewById(R.id.editTextIdentityKey)

            val nameString = nameEditText.text.toString()
            val streetString = streetEditText.text.toString()
            val businessString = businessEditText.text.toString()
            val passwordString = passwordEditText.text.toString()
            val zipString = zipEditText.text.toString()
            val phoneString = phoneEditText.text.toString()
            val mailString = mailEditText.text.toString()

            val newBusinessOwner = BusinessUser(
                nameString,
                businessString,
                streetString,
                zipString,
                phoneString,
                mailString,
                passwordString
            )

            DataManager.businessUserList.add(newBusinessOwner)
            DataManager.createNewUser(newBusinessOwner, mailString, passwordString)

            // Nedan är lite check i LogCat på att alla uppgifter sparats
            val printNewUser = DataManager.businessUserList[DataManager.businessUserList.lastIndex]
            Log.d(TAG, "${DataManager.businessUserList.lastIndex}")
            Log.d(TAG, "New user name: ${printNewUser.name}")
            Log.d(TAG, "New user business: ${printNewUser.business}")
            Log.d(TAG, "New user street: ${printNewUser.addressStreet}")
            Log.d(TAG, "New user zipcode: ${printNewUser.townZipCode}")
            Log.d(TAG, "New user phone: ${printNewUser.phone}")
            Log.d(TAG, "New user mail: ${printNewUser.email}")
            Log.d(TAG, "New user password: ${printNewUser.password}")

            val intent = Intent(this, LoginScreenActivity::class.java)
            startActivity(intent)
            Toast.makeText(applicationContext, "Account created !", Toast.LENGTH_SHORT).show()
        }
    }
}