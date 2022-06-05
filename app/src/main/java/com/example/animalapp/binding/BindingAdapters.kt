package com.example.animalapp.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

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