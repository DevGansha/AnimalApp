package com.example.animalapp.ui.newAnimal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.animalapp.R
import com.example.animalapp.data.model.AnimalDetail
import com.example.animalapp.data.model.AnimalDetailRequest
import com.example.animalapp.data.model.Breed
import com.example.animalapp.databinding.FrNewAnimalBinding
import com.example.animalapp.ui.viewmodels.AnimalListingViewModel
import com.example.animalapp.ui.viewmodels.NewAnimalViewModel
import com.example.animalapp.util.Util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewAnimalFragment : Fragment() {
    lateinit var frNewAnimalBinding: FrNewAnimalBinding
    val newAnimalViewModel: NewAnimalViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        frNewAnimalBinding =
            DataBindingUtil.inflate(inflater, R.layout.fr_new_animal, container, false)
        return frNewAnimalBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lateinit var animal : AnimalDetailRequest
        frNewAnimalBinding.submitBtn.setOnClickListener {
            frNewAnimalBinding.let {
                animal = AnimalDetailRequest(
                    //id =  "", //it.idTxt.text.toString(),
                    name = it.nameTxt.text.toString(),
                    kind = it.kindTxt.text.toString(),
                    breed = Breed(id= it.breedIdTxt.text.toString(), name = it.breedNameTxt.text.toString(), description = it.breedDescTxt.text.toString()),
                    description = it.descTxt.text.toString(),
                    age = Integer.parseInt(it.ageTxt.text.toString()),
                    imageUrl = "String" //https://upload.wikimedia.org/wikipedia/commons/thumb/a/a4/Racib%C3%B3rz_2007_082.jpg/1024px-Racib%C3%B3rz_2007_082.jpg"
                )
            }

            //newAnimalViewModel.insertNewAnimal(animal)

            it.context.toast("Application crashes here because of API issues")
        }
    }
}