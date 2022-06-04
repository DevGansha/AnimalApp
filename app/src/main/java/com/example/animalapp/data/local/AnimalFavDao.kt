package com.example.animalapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import com.example.animalapp.data.model.AnimalDetail

@Dao
interface AnimalFavDao {
    @Insert
    fun insertAnimal(animalDetail: AnimalDetail)
}