package com.example.animalapp.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.animalapp.R

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    //the Actual URL was not working that's why.
    val img = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a4/Racib%C3%B3rz_2007_082.jpg/1024px-Racib%C3%B3rz_2007_082.jpg"
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(img)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("bindValue")
fun bindToTextView(view: TextView, age: Int){
    if(age < 1){
        view.text = "Puppy"
    }else if(age in 2..6){
        view.text = "Adult "
    }else{
        view.text = "Senior"
    }
}

@BindingAdapter("colorImgChange")
fun bindImageFromUrl(view: ImageView, fav_state: Int) {
    if(fav_state == 0){
        view.setImageResource(R.mipmap.white_heart)
    }else{
        view.setImageResource(R.mipmap.heart_filled)
    }
}
