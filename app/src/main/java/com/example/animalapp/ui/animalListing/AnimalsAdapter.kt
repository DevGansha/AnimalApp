package com.example.animalapp.ui.animalListing

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animalapp.R
import com.example.animalapp.data.model.AnimalDetail
import com.example.animalapp.databinding.LiAnimalBinding

class AnimalsAdapter(val context: Context, val recyclerViewHome: RecyclerViewHomeClickListener) : RecyclerView.Adapter<ViewHolder>(){

    private lateinit var recyclerView: RecyclerView
    var items: Array<AnimalDetail> = arrayOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LiAnimalBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.get(position)
        item.let {
            holder.apply {
                bind(item, isLinearLayoutManager())
                itemView.tag = item
            }
        }

        holder.itemView.setOnClickListener {
            recyclerViewHome.clickOnItem(
                item,
                holder.itemView
            )
        }
        holder.binding.fabFav.setOnClickListener {
            holder.binding.fabFav.setImageResource(R.mipmap.heart_filled)
            recyclerViewHome.clickFav(item)
        }

    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(itemList: Array<AnimalDetail>){
        //items.addAll(itemList)
        items = itemList
        notifyDataSetChanged()
    }

    private fun isLinearLayoutManager() = recyclerView.layoutManager is LinearLayoutManager
}

class ViewHolder(val binding: LiAnimalBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: AnimalDetail, isLinearLayoutManager: Boolean) {
        binding.apply {
            animal = data
            executePendingBindings()
        }
    }
}
interface RecyclerViewHomeClickListener {
    fun clickOnItem(data: AnimalDetail, card: View)
    fun clickFav(data: AnimalDetail)
}


