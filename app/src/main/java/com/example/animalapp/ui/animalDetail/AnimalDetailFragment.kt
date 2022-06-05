package com.example.animalapp.ui.animalDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.animalapp.R
import com.example.animalapp.databinding.FrAnimalDetailScreenBinding
import com.example.animalapp.ui.viewmodels.AnimalDetailViewModel
import com.example.animalapp.util.Resource
import com.example.animalapp.util.Util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimalDetailFragment: Fragment() {
    var animal_id = ""
    lateinit var frAnimalDetailScreenBinding: FrAnimalDetailScreenBinding
    val animalDetailViewModel: AnimalDetailViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        frAnimalDetailScreenBinding = DataBindingUtil.inflate(inflater, R.layout.fr_animal_detail_screen, container, false)
        return frAnimalDetailScreenBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        animal_id = getArguments()?.getString("AnimalID") ?: ""

        if(animal_id != "") animalDetailViewModel.getAnimalDetail(animal_id)

        observeUI()
    }

    fun observeUI(){
         animalDetailViewModel.animal.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success -> {
                    frAnimalDetailScreenBinding.apply {
                        progress.visibility= View.GONE
                        animalName.visibility = View.VISIBLE
                        animalKind.visibility = View.VISIBLE
                        animalAge.visibility = View.VISIBLE
                        animalDesc.visibility = View.VISIBLE
                        animal = it.data
                    }
                }
                is Resource.Error -> {
                    context?.toast(it.message.toString())
                }

                is Resource.Loading -> {
                    frAnimalDetailScreenBinding.apply {
                        progress.visibility= View.VISIBLE
                        animalName.visibility = View.GONE
                        animalKind.visibility = View.GONE
                        animalAge.visibility = View.GONE
                        animalDesc.visibility = View.GONE
                    }
                }
            }
        }
    }
}