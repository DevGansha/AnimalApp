package com.example.animalapp.ui.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animalapp.data.local.entity.AnimalDetailTable
import com.example.animalapp.data.local.entity.BreedTable
import com.example.animalapp.data.model.AnimalDetail
import com.example.animalapp.data.repository.AnimalRepo
import com.example.animalapp.util.Resource
import com.example.animalapp.util.hasInternetConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class AnimalListingViewModel  @Inject constructor(
    private val animalRepo: AnimalRepo,
    @ApplicationContext private val context: Context
) : ViewModel() {

    val animalList: MutableLiveData<Resource<Array<AnimalDetail>>> = MutableLiveData()

    init {
        fetchAnimals()
    }
    fun fetchAnimals(){
        animalList.postValue(Resource.Loading())

        viewModelScope.launch {
            try {
                if (hasInternetConnection(context)) {
                    val response = animalRepo.fetchAnimals()

                    if (response.isSuccessful) {
                       animalList.postValue(Resource.Success(response.body()!!))
                    } else{
                        animalList.postValue(Resource.Error(response.message()))
                    }
                }
                else{
                    animalList.postValue(Resource.Error("No Internet Connection"))
                }
            }catch (ex: Exception) {
                when (ex) {
                    is IOException -> animalList.postValue(Resource.Error("Network Failure " + ex.localizedMessage))
                    else -> animalList.postValue(Resource.Error("Conversion Error"))
                }
            }
        }
    }

    fun addAnimalAsFav(animalDetail: AnimalDetail){
       viewModelScope.launch {
           lateinit var animalBreed : BreedTable
           animalDetail.breed.let {
               var animalBreed = BreedTable(
                   id = it.id,
                   name = it.name,
                   description = it.description
               )
               val s: Long = animalRepo.saveBreed(animalBreed)
               //Toast.makeText(this@AnimalListingViewModel.context, s.toString(), Toast.LENGTH_SHORT).show()
           }

           var breedId = animalRepo.getBreedId(animalDetail.breed.id)

           var animalDetailTable = AnimalDetailTable(
               id = animalDetail.id,
               name = animalDetail.name,
               description = animalDetail.description,
               imageUrl = animalDetail.imageUrl,
               kind = animalDetail.kind,
               age = animalDetail.age,
               breed = animalBreed )

           animalDetailTable.let {
               //val sucess : Long =
               animalRepo.saveAnimalAsFav(it)
           }
       }
    }
}