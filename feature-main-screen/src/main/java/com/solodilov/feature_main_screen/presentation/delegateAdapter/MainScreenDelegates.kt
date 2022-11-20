package com.solodilov.feature_main_screen.presentation.delegateAdapter

import android.app.Activity
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import androidx.recyclerview.widget.PagerSnapHelper
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.solodilov.core.databinding.ErrorLayoutBinding
import com.solodilov.feature_main_screen.R
import com.solodilov.feature_main_screen.databinding.*
import com.solodilov.feature_main_screen.presentation.delegateAdapter.model.*
import java.util.*

object MainScreenDelegates {

    fun hotSaleBlockDelegate() =
        adapterDelegateViewBinding<HotSaleBlockUi, ItemUi, ItemHotSalesBlockBinding>(
            { inflater, container -> ItemHotSalesBlockBinding.inflate(inflater, container, false) }
        ) {
            val adapter = HotSaleAdapter()
            binding.hotSalesSlider.adapter = adapter
            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(binding.hotSalesSlider)
            bind {
                adapter.items = item.hotSaleList
            }
        }

    fun bestSellerBlockDelegate(onProductClick: (ItemUi) -> Unit) =
        adapterDelegateViewBinding<BestSellerBlockUi, ItemUi, ItemBestSallerBlockBinding>(
            { inflater, container ->
                ItemBestSallerBlockBinding.inflate(inflater,
                    container,
                    false)
            }
        ) {
            val adapter = BestSellerAdapter(onProductClick)
            binding.bestSellerList.adapter = adapter

            bind {
                adapter.items = item.bestSellerList
            }
        }

    fun hotSaleDelegate() =
        adapterDelegateViewBinding<HotSaleItemUi, ItemUi, ItemHotSalesBinding>(
            { inflater, container -> ItemHotSalesBinding.inflate(inflater, container, false) }
        ) {
            bind {
                Glide.with(binding.root)
                    .load(item.picture)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.hotSalesImage)
                binding.hotSalesTitle.text = item.title
                binding.hotSalesLabel.isVisible = item.isNew
                binding.hotSalesSubtitle.text = item.subtitle
            }

            onViewRecycled {
                if ((binding.root.context as? Activity)?.isDestroyed?.not() == true) {
                    Glide.with(binding.root).clear(binding.hotSalesImage)
                }
            }
        }

    fun bestSellerDelegate(onProductClick: (ItemUi) -> Unit) =
        adapterDelegateViewBinding<BestSellerItemUi, ItemUi, ItemBestSellerBinding>(
            { inflater, container -> ItemBestSellerBinding.inflate(inflater, container, false) }
        ) {
            bind {
                Glide.with(binding.root)
                    .load(item.picture)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.bestSellerImage)

                val favoriteImage = if (item.isFavorites) {
                    R.drawable.ic_best_seller_favorite_true
                } else {
                    R.drawable.ic_best_seller_favorite_false
                }

                binding.bestSellerFavorite.setImageResource(favoriteImage)
                binding.bestSellerTitle.text = item.title
                binding.bestSellerPrice.text = String.format(
                    Locale.ENGLISH,
                    getString(R.string.best_seller_price_format),
                    item.price
                )
                binding.bestSellerFullPrice.text = String.format(
                    Locale.ENGLISH,
                    getString(R.string.best_seller_price_format),
                    item.fullPrice
                )

                binding.root.setOnClickListener { onProductClick(item) }
            }

            onViewRecycled {
                if ((binding.root.context as? Activity)?.isDestroyed?.not() == true) {
                    Glide.with(binding.root).clear(binding.bestSellerImage)
                }
            }
        }

    fun hotSaleProgressDelegate() =
        adapterDelegateViewBinding<ProgressHotSaleItem, ItemUi, ItemProgressHotSalesBinding>(
            { inflater, container ->
                ItemProgressHotSalesBinding.inflate(inflater,
                    container,
                    false)
            }
        ) {
            val animation =
                AnimationUtils.loadAnimation(binding.root.context, R.anim.progress_fade_out)
            binding.root.startAnimation(animation)
        }

    fun bestSellerProgressDelegate() =
        adapterDelegateViewBinding<ProgressBestSellerItem, ItemUi, ItemProgressBestSellerBinding>(
            { inflater, container ->
                ItemProgressBestSellerBinding.inflate(inflater,
                    container,
                    false)
            }
        ) {
            val animation =
                AnimationUtils.loadAnimation(binding.root.context, R.anim.progress_fade_out)
            binding.root.startAnimation(animation)
        }

    fun mainErrorDelegate(onRefreshClick: () -> Unit) =
        adapterDelegateViewBinding<MainScreenErrorItem, ItemUi, ErrorLayoutBinding>(
            { inflater, container ->
                ErrorLayoutBinding.inflate(inflater, container, false)
            }
        ) {
            bind {
                binding.tryButton.setOnClickListener { onRefreshClick() }
            }
        }
}