package com.solodilov.feature_cart_screen.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.solodilov.feature_cart_screen.databinding.ItemCartProductBinding
import com.solodilov.feature_cart_screen.domain.entity.CartProduct
import java.text.DecimalFormat

class CartProductAdapter
    : ListAdapter<CartProduct, CartProductViewHolder>(CartProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductViewHolder =
        CartProductViewHolder(ItemCartProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))

    override fun onBindViewHolder(holder: CartProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CartProductViewHolder(
    private val binding: ItemCartProductBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(cartProduct: CartProduct) {

        binding.apply {
            cartProductTitle.text = cartProduct.title
            cartProductPrice.text = "$${DecimalFormat("###.00").format(cartProduct.price)}"
            cartProductImage
            productCount.text = cartProduct.quantity.toString()
        }

        Glide
            .with(itemView)
            .load(cartProduct.images)
            .transform(CenterCrop(), RoundedCorners(10))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.cartProductImage)
    }
}

private class CartProductDiffCallback : DiffUtil.ItemCallback<CartProduct>() {

    override fun areItemsTheSame(oldItem: CartProduct, newItem: CartProduct): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CartProduct, newItem: CartProduct): Boolean {
        return oldItem == newItem
    }
}