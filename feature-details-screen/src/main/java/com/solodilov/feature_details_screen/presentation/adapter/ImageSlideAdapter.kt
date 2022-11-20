package com.solodilov.feature_details_screen.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.solodilov.feature_details_screen.databinding.ItemImageSliderBinding

class ImageSlideAdapter (private val imageUrlList: List<String>) :
    RecyclerView.Adapter<ImageSlideAdapter.PagerViewHolder>() {

    inner class PagerViewHolder(private val binding: ItemImageSliderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(imageUrl: String) {

            Glide
                .with(itemView)
                .load(imageUrl)
                .into(binding.productImage)
        }
    }

    override fun getItemCount(): Int = imageUrlList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder =
        PagerViewHolder(ItemImageSliderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {

        holder.bind(imageUrlList[position])
    }
}