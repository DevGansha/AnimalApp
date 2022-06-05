package com.example.animalapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breed_tbl")
data class BreedTable (
    @PrimaryKey var id: String,
    var name: String,
    var description: String
)