package com.example.projekt7.Model

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

const val TAG = "!!!"

object DataManager {
    var regularUserList = mutableListOf<RegularUser>()
    var businessUserList = mutableListOf<BusinessUser>()
    var auth = Firebase.auth

    fun createNewUser(userObject: Any, email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) return

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createUser: Success")

                    auth.signInWithEmailAndPassword(email, password)
                        .addOnSuccessListener {
                            addUserToFirebase(userObject)
                        }
                } else {
                    Log.d(TAG, "createUser: user not created ${task.exception}")
                }
            }
    }

    private fun addUserToFirebase(userObject: Any) {
        val db = Firebase.firestore

        db.collection("users").document(auth.currentUser!!.uid)
            .collection("data").add(userObject)
            .addOnCompleteListener {
                Log.d("!!!", "saveItem: add: ${it.exception}")
                auth.signOut()
            }
    }
}
