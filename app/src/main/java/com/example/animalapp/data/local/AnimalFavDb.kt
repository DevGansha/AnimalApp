package com.example.animalapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.animalapp.data.model.AnimalDetail
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [AnimalDetail::class], version = 1)
abstract class AnimalFavDb: RoomDatabase() {

    abstract fun getAnimalFavDao(): AnimalFavDao

    class Callback @Inject constructor(
        private val database: Provider<AnimalFavDb>) : RoomDatabase.Callback()
}