package com.example.animalapp.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class AnimalDetail(
    @SerializedName("id")
    var id: String,
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
