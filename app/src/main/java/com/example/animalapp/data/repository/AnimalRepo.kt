package com.example.animalapp.data.repository

import com.example.animalapp.data.model.AnimalDetail
import com.example.animalapp.data.remote.AnimalService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimalRepo @Inject constructor(
    private val animalService: AnimalService) {

    suspend fun fetchAnimals(): Response<AnimalDetail> = withContext(
        Dispatchers.IO) {
        val allAnimals = animalService.getAllAnimals()
        allAnimals
    }

}