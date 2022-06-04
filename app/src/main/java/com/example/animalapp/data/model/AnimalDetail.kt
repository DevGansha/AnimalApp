package com.example.animalapp.data.model

data class AnimalDetail(
    var id: String,
    var name: String,
    var kind: String,
    var breed: Breed,
    var description: String,
    var age: Int,
    var imageUrl: String
)
