package com.example.animalapp.data.remote

import com.example.animalapp.data.model.AnimalDetail
import retrofit2.Response
import retrofit2.http.GET

interface AnimalService {

    @GET("animals")
    suspend fun getAllAnimals(): Response<AnimalDetail>
}