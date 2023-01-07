package com.example.projekt7

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CreateRUActivity : AppCompatActivity() {
    lateinit var nameEditText: EditText
    lateinit var mailEditText: EditText
    lateinit var userNameEditText: EditText
    lateinit var passwordEditText: EditText
    private val mailPattern = "[a-zA-Z0-9._-]+@+[a-z]+.+[a-z]+"
    private val usernamePattern = "[a-zA-Z0-9]+"
    private val namePattern = "[a-zA-Z]+"

    val TAG = "!!!"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_ruactivity)

        supportActionBar?.hide()

        val createUserButton = findViewById<Button>(R.id.buttonCreateUser)
        createUserButton.setOnClickListener() {
            nameEditText = findViewById(R.id.businessName)
            mailEditText = findViewById(R.id.editTextMail)
            userNameEditText = findViewById(R.id.editTextUserName)
            passwordEditText = findViewById(R.id.editTextPassword)

            var nameString = nameEditText.getText().toString()
            var mailString = mailEditText.getText().toString()
            var userNameString = userNameEditText.getText().toString()
            var passwordString = passwordEditText.getText().toString()

            val newRegularUser = RegularUser(
                nameString, mailString,
                userNameString, passwordString
            )

            if (mailString.isEmpty() || passwordString.isEmpty() || userNameString.isEmpty() || nameString.isEmpty()) {
                Toast.makeText(
                    applicationContext,
                    "Some information is missing",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            else if (mailString.matches(mailPattern.toRegex())) {
                if (passwordString.length>7) {
                    if (userNameString.matches(usernamePattern.toRegex())) {
                        if (nameString.matches(namePattern.toRegex())) {
                            DataManager.regularUserList.add(newRegularUser)

                            //Nedan är lite check i LogCat på att alla uppgifter sparats
                            var printNewUser = DataManager.regularUserList[DataManager.regularUserList.lastIndex]
                            Log.d(TAG, "${DataManager.regularUserList.lastIndex}")
                            Log.d(TAG, "New user name: ${printNewUser.name}")
                            Log.d(TAG, "New user mail: ${printNewUser.mail}")
                            Log.d(TAG, "New username: ${printNewUser.userName}")
                            Log.d(TAG, "New user password: ${printNewUser.password}")


                            DataManager.createRU(mailString, passwordString)

                            Toast.makeText(applicationContext, "Account created!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, LoginScreenActivity::class.java)
                            startActivity(intent)
                        }
                        else{
                            Toast.makeText(
                                applicationContext, "Please enter a valid first name!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    else {
                        Toast.makeText(
                            applicationContext, "Your username can only contain letters,numbers and '_ or .'!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                else{
                    Toast.makeText(
                        applicationContext, "Your password needs to be atleast 8 characters!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            else {
                Toast.makeText(
                    applicationContext, "Please enter an valid mail!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
