package com.example.animalapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.animalapp.data.local.entity.AnimalDetailTable
import com.example.animalapp.data.local.entity.BreedTable
import com.example.animalapp.data.model.AnimalDetail
import com.example.animalapp.data.model.Breed
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [AnimalDetailTable::class, BreedTable::class], version = 1)
abstract class AnimalFavDb: RoomDatabase() {

    abstract fun getAnimalFavDao(): AnimalFavDao

    class Callback @Inject constructor(
        private val database: Provider<AnimalFavDb>) : RoomDatabase.Callback()
}