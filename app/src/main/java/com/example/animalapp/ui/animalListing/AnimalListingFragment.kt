package com.example.animalapp.ui.animalListing

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.animalapp.R
import com.example.animalapp.data.model.AnimalDetail
import com.example.animalapp.databinding.FragmentAnimalListingBinding
import com.example.animalapp.ui.viewmodels.AnimalListingViewModel
import com.example.animalapp.util.Resource
import com.example.animalapp.util.Util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimalListingFragment : Fragment(), RecyclerViewHomeClickListener{

    var fav_state = 0
    val animalsListingViewModels: AnimalListingViewModel by viewModels()
    lateinit var fragmentAnimalListingBinding: FragmentAnimalListingBinding
    private val animalsAdapter: AnimalsAdapter by lazy { AnimalsAdapter(requireContext(), this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentAnimalListingBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_animal_listing, container, false)
        return fragmentAnimalListingBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentAnimalListingBinding.recyclerView.apply {
            adapter = animalsAdapter
        }
        animalsListingViewModels.fetchAnimals()
        observeUI()

        fragmentAnimalListingBinding.fabRefresh.setOnClickListener {
            if(fav_state == 0){
                fragmentAnimalListingBinding.fabRefresh.setImageResource(R.mipmap.heart_filled)
                animalsListingViewModels.getAllFavourites()
                fav_state = 1
            }else {
                fragmentAnimalListingBinding.fabRefresh.setImageResource(R.mipmap.white_heart)
                animalsListingViewModels.fetchAnimals()
                fav_state = 0
            }
        }

        fragmentAnimalListingBinding.fabNewAnimal.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_animalListingFragment_to_newAnimalFragment)
        }
    }

    fun observeUI(){
        animalsListingViewModels.animalList.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success -> {
                    fragmentAnimalListingBinding.progress.visibility = View.GONE
                    fragmentAnimalListingBinding.recyclerView.visibility = View.VISIBLE
                    fragmentAnimalListingBinding.errorTxt.visibility = View.GONE

                    val data = it.data
                    animalsAdapter.submitList(data!!, fav_state)
                }
                is Resource.Error -> {
                    fragmentAnimalListingBinding.progress.visibility = View.GONE
                    it.message?.let { message ->
                        fragmentAnimalListingBinding.recyclerView.visibility = View.GONE
                        fragmentAnimalListingBinding.errorTxt.visibility = View.VISIBLE
                        fragmentAnimalListingBinding.errorTxt.text = message
                    }
                }

                is Resource.Loading -> {
                    fragmentAnimalListingBinding.recyclerView.visibility = View.GONE
                    fragmentAnimalListingBinding.errorTxt.visibility = View.GONE
                    fragmentAnimalListingBinding.progress.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun clickOnItem(data: AnimalDetail, card: View) {
        val bundle = Bundle()
        bundle.putString("AnimalID", data.id)
        Navigation.findNavController(card).navigate(R.id.action_animalListingFragment_to_animalDetailFragment, bundle)
    }

    override fun clickFav(animalDetail: AnimalDetail) {
        animalsListingViewModels.addAnimalAsFav(animalDetail)
    }
}