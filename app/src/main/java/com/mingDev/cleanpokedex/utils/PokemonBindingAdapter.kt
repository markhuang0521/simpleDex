package com.mingDev.cleanpokedex.utils

import android.animation.ObjectAnimator
import android.content.res.ColorStateList
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.mingDev.cleanpokedex.R
import com.mingDev.cleanpokedex.database.entity.MoveSetDto
import com.mingDev.cleanpokedex.database.entity.PokemonDto
import com.mingDev.cleanpokedex.ui.MoveSetAdapter
import com.mingDev.cleanpokedex.ui.PokemonAdapter
import com.mingDev.cleanpokedex.ui.PokemonTypeAdapter


@BindingAdapter("moveSetLiveData")
fun setMoveSetLiveData(recyclerView: RecyclerView, list: LiveData<List<MoveSetDto>>?) {
    list?.value?.let {
        val adapter = recyclerView.adapter as MoveSetAdapter
        adapter.submitList(it)

    }
}


@BindingAdapter("pokemonLiveData")
fun setPokemonLiveData(recyclerView: RecyclerView, list: LiveData<List<PokemonDto>>?) {
    list?.value?.let {
        val adapter = recyclerView.adapter as PokemonAdapter
        adapter.submitList(it)

    }

}

@BindingAdapter("typeLiveData")
fun setTypeLiveData(recyclerView: RecyclerView, list: List<String>?) {
    list?.let {
        val adapter = PokemonTypeAdapter()
        recyclerView.layoutManager =
            LinearLayoutManager(recyclerView.context, LinearLayoutManager.HORIZONTAL, false)

        recyclerView.adapter = adapter
        adapter.addData(list)

    }

}

@BindingAdapter("pokemonName")
fun setPokemonName(textView: TextView, string: String) {
    if (string.contains("-")) {

        val arr: List<String> = string.split("-")
        if (arr.size > 2) {
            textView.text = "${arr[0]}\n(${arr[1]} ${arr[2]})"
        } else {
            textView.text = "${arr[0]}\n(${arr[1]})"
        }
    } else {
        textView.text = string
    }
}


@BindingAdapter("pokemonImageUrl")
fun setGlideUrl(imageView: ImageView, pokemonDto: PokemonDto?) {
    pokemonDto?.let {
        Glide.with(imageView.context)
            .load(pokemonDto.imageUrl)
            .centerCrop()
            .fitCenter()
            .placeholder(R.drawable.poke_ball)
            .error(pokemonDto.backUpImageUrl)
            .into(imageView)
    }

}

@BindingAdapter("itemImageUrl")
fun setItemIMageUrl(imageView: ImageView, url: String?) {
    Glide.with(imageView.context)
        .load(url)
        .centerCrop()
        .fitCenter()
        .placeholder(R.drawable.poke_ball)
        .into(imageView)


}


@BindingAdapter("progressAnimation")
fun showProgressAnimation(progressBar: ProgressBar, progress: Int) {
    val animation: ObjectAnimator = ObjectAnimator.ofInt(progressBar, "progress", progress)
    animation.duration = 1000
    animation.interpolator = AccelerateInterpolator()
    animation.start()
}


@BindingAdapter("backgroundColorByType")
fun setBackgroundColorByType(cardView: MaterialCardView, type: String?) {

    val id: Int = cardView.resources.getIdentifier(
        type, "color", cardView.context.packageName
    )
    val color = ContextCompat.getColor(cardView.context, id)

    cardView.setCardBackgroundColor(color)
}

@BindingAdapter("textColorByType")
fun setTextColorByTypeByType(view: TextView, type: String) {
    val id: Int = view.resources.getIdentifier(
        type, "color", view.context.packageName
    )
    val color = ContextCompat.getColor(view.context, id)
    view.setTextColor(color)
}


@BindingAdapter("textBackgroundByType")
fun setBackgroundTineByType(view: TextView, type: String) {

    val id: Int = view.resources.getIdentifier(
        type, "color", view.context.packageName
    )
    val color = ContextCompat.getColor(view.context, id)
    view.setBackgroundResource(R.drawable.text_view_round_bg)
    view.setBackgroundColor(color)

}

@BindingAdapter("layoutBackgroundColorByType")
fun setLayoutBackgroundColorByType(view: ConstraintLayout, type: String) {

    val id: Int = view.resources.getIdentifier(
        type, "color", view.context.packageName
    )
    val color = ContextCompat.getColor(view.context, id)

    view.setBackgroundColor(color)
}

@BindingAdapter("layoutBackgroundColorByType")
fun setLayoutBackgroundColorByType(view: LinearLayout, type: String) {

    val id: Int = view.resources.getIdentifier(
        type, "color", view.context.packageName
    )
    val color = ContextCompat.getColor(view.context, id)

    view.setBackgroundColor(color)
}

@BindingAdapter("progressColorByType")
fun setLayoutBackgroundColorByType(view: ProgressBar, type: String) {

    val id: Int = view.resources.getIdentifier(
        type, "color", view.context.packageName
    )
    val color = ContextCompat.getColor(view.context, id)

    view.progressTintList = ColorStateList.valueOf(color)
}


@BindingAdapter("viewVisible")
fun setViewVisibility(view: View, visible: Boolean? = false) {
    if (visible == true) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter("viewExist")
fun setViewVisibility(view: View, text: String? = null) {
    if (!text.isNullOrEmpty()) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

//@BindingAdapter("backgroundColorByTypes")
//fun setBackgroundColorByTypes(cardView: MaterialCardView, types: List<PokemonTypes>?) {
//
//    types?.let {
//        val type = types[0].type.name
//        val id: Int =
//            cardView.resources.getIdentifier(type, "color", cardView.context.packageName)
//        val color = ContextCompat.getColor(cardView.context, id)
//
//        cardView.setCardBackgroundColor(color)
//    }
//}