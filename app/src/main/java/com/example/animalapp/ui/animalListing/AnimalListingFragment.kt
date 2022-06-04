package com.example.animalapp.ui.animalListing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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

    val animalsListingViewModels: AnimalListingViewModel by viewModels()
    lateinit var fragmentAnimalListingBinding: FragmentAnimalListingBinding
    private val productsAdapter: AnimalsAdapter by lazy { AnimalsAdapter(requireContext(), this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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

        animalsListingViewModels.fetchAnimals()
        observeUI()
    }

    fun observeUI(){
         animalsListingViewModels.animalList.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success -> {
                    fragmentAnimalListingBinding.progress.visibility = View.GONE
                    val data = it.data
                    //productsAdapter.submitList(data!!)
                }
                is Resource.Error -> {
                    fragmentAnimalListingBinding.progress.visibility = View.GONE
                    it.message?.let { message ->
                        context?.toast(message)
                    }
                }

                is Resource.Loading -> {
                    fragmentAnimalListingBinding.progress.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun clickOnItem(data: AnimalDetail, card: View) {
        val bundle = Bundle()
        bundle.putString("AnimalID", data.id)
        //Navigation.findNavController(card).navigate(R.id.action_productListingFragment_to_productDetailFragment, bundle)
        // context?.toast(data.toString())
    }
}