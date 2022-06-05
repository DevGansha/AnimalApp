package com.example.animalapp.data.local

import androidx.room.*
import com.example.animalapp.data.local.entity.AnimalDetailTable
import com.example.animalapp.data.local.entity.BreedTable
import com.example.animalapp.data.local.relations.AnimalAndBreed
import com.example.animalapp.data.model.AnimalDetail
import retrofit2.Response

@Dao
interface AnimalFavDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBreed(breedTable: BreedTable): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAnimal(animalDetailTable: AnimalDetailTable): Long

    @Query("select breed.id from breed_tbl breed where breed.id = :id")
    suspend fun getBreedId(id: String): String

    @Transaction
    @Query("select * from animal_detail_tbl a inner join breed_tbl b on a.breed = b.id")
    suspend fun getALlFavourites(): Array<AnimalAndBreed>
}