package com.example.animalapp.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "animal_detail_tbl",
    foreignKeys = [ForeignKey(
        entity = BreedTable::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("breed"),
        onDelete = ForeignKey.CASCADE
    )])
data class AnimalDetailTable(
    @PrimaryKey var id: String,
    var name: String,
    var kind: String,
    var breed: String,
    var description: String,
    var age: Int,
    var imageUrl: String
)
