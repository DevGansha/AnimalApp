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
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class AnimalDetailViewModel @Inject constructor(
    private val animalRepo: AnimalRepo,
    @ApplicationContext private val context: Context
) : ViewModel() {

    val animal: MutableLiveData<Resource<AnimalDetail>> = MutableLiveData()

    fun getAnimalDetail(animal_id: String){
        animal.postValue(Resource.Loading())

        viewModelScope.launch {
            try {
                if (hasInternetConnection(context)) {
                    val response = animalRepo.getAnimal(animal_id)
                    if (response.isSuccessful) {
                        animal.postValue(Resource.Success(response.body()!!))
                    } else{
                        animal.postValue(Resource.Error(response.message()))
                    }
                }
                else{
                    animal.postValue(Resource.Error("No Internet Connection"))
                }
            }catch (ex: Exception) {
                when (ex) {
                    is IOException -> animal.postValue(Resource.Error("Network Failure " + ex.localizedMessage))
                    else -> animal.postValue(Resource.Error(ex.message.toString()))
                }
            }
        }
    }
}