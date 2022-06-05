package com.example.animalapp.data.remote

import com.example.animalapp.data.model.AnimalDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimalService {

    @GET("animals")
    suspend fun getAllAnimals(): Response<Array<AnimalDetail>>

    @GET("animals/{animalId}")
    suspend fun getAnimal(@Path("animalId") animalId: String): Response<AnimalDetail>
}