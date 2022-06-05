package com.example.animalapp.data.remote

import com.example.animalapp.data.model.AnimalDetail
import com.example.animalapp.data.model.AnimalDetailRequest
import com.example.animalapp.data.model.Breed
import com.example.animalapp.data.model.ErrorClass
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface AnimalService {

    @GET("/animals")
    suspend fun getAllAnimals(): Response<Array<AnimalDetail>>

    @GET("/animals/{animalId}")
    suspend fun getAnimal(@Path("animalId") animalId: String): Response<AnimalDetail>

    @Headers("Content-Type:application/json")
    @FormUrlEncoded
    @POST("/animals")
//   suspend fun insertAnimal(@Body animalDetail: AnimalDetailRequest): Long
    suspend fun insertAnimal(@Field("name") animalName: String,
                             @Field("kind") animalKind: String,
                             @Field("breed") breed: Breed,
                             @Field("description") description: String,
                             @Field("age") age: Int,
                             @Field("imageUrl") imageUrl: String): Call<ResponseBody>
}