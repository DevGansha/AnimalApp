package com.example.animalapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.animalapp.data.local.entity.AnimalDetailTable
import com.example.animalapp.data.local.entity.BreedTable
import com.example.animalapp.data.model.AnimalDetail

@Dao
interface AnimalFavDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBreed(breedTable: BreedTable): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAnimal(animalDetailTable: AnimalDetailTable): Long

    @Query("select breed.id from breed_tbl breed where breed.id = :id")
    suspend fun getBreedId(id: String): String
}