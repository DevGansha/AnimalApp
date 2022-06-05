package com.example.animalapp.data.model

import com.google.gson.annotations.SerializedName

data class AnimalDetailRequest(
    @SerializedName("name")
    var name: String,
    @SerializedName("kind")
    var kind: String,
    @SerializedName("breed")
    var breed: Breed,
    @SerializedName("description")
    var description: String,
    @SerializedName("age")
    var age: Int,
    @SerializedName("imageUrl")
    var imageUrl: String
)
