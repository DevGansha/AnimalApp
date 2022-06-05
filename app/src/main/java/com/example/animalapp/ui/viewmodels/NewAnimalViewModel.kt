package com.example.animalapp.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animalapp.data.model.AnimalDetail
import com.example.animalapp.data.model.AnimalDetailRequest
import com.example.animalapp.data.repository.AnimalRepo
import com.example.animalapp.util.Util.toast
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewAnimalViewModel @Inject constructor(
    private val animalRepo: AnimalRepo,
    @ApplicationContext private val context: Context
) : ViewModel() {

    fun insertNewAnimal(animalDetail: AnimalDetailRequest) {
        viewModelScope.launch {
            var sucess = animalRepo.insertAnimal(animalDetail)

            context.toast(sucess.toString())
        }
    }
}