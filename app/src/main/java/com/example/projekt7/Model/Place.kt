package com.example.projekt7.Model

import com.google.firebase.database.PropertyName
import java.io.Serializable

data class Place (var title : String?, var description: String?, var latitude: Double, var longitude: Double, var user: User? = null,
                  @get:PropertyName("image_url") @set:PropertyName("image_url") var imageUrl: String? = null) : Serializable