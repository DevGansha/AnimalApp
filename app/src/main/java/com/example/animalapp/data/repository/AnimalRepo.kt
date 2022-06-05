package com.example.animalapp.data.repository

import com.example.animalapp.data.local.AnimalFavDao
import com.example.animalapp.data.local.entity.AnimalDetailTable
import com.example.animalapp.data.local.entity.BreedTable
import com.example.animalapp.data.local.relations.AnimalAndBreed
import com.example.animalapp.data.model.AnimalDetail
import com.example.animalapp.data.remote.AnimalService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimalRepo @Inject constructor(
    private val animalService: AnimalService, private val animalFavDao: AnimalFavDao) {

    suspend fun fetchAnimals(): Response<Array<AnimalDetail>> = withContext(
        Dispatchers.IO) {
        val allAnimals = animalService.getAllAnimals()
        allAnimals
    }

    suspend fun getAnimal(animalId: String): Response<AnimalDetail> = withContext(Dispatchers.IO){
        val animal = animalService.getAnimal(animalId)
        animal
    }
    suspend fun saveAnimalAsFav(animalDetailTable: AnimalDetailTable): Long = withContext(Dispatchers.IO){
        animalFavDao.insertAnimal(animalDetailTable)
    }

    suspend fun saveBreed(breedTable: BreedTable): Long = withContext(Dispatchers.IO){
        animalFavDao.insertBreed(breedTable)
    }

    suspend fun getBreedId(breed_id: String): String = withContext(Dispatchers.IO){
        animalFavDao.getBreedId(breed_id)
    }

    suspend fun getAllFavourites(): Array<AnimalAndBreed> = withContext(Dispatchers.IO){
        animalFavDao.getALlFavourites()
    }
}