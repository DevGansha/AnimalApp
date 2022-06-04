package com.example.animalapp.ui.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    val animalList: MutableLiveData<Resource<AnimalDetail>> = MutableLiveData()

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
}