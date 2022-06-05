package com.example.animalapp.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.animalapp.data.local.entity.AnimalDetailTable
import com.example.animalapp.data.local.entity.BreedTable

data class AnimalAndBreed (
    @Embedded
    val animalDetailTable: AnimalDetailTable,
    @Relation(
        parentColumn = "breed",
        entityColumn = "id"
    )
    val breedTable: BreedTable
)