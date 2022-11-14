package com.solodilov.feature_main_screen.presentation.delegateAdapter

import androidx.recyclerview.widget.DiffUtil
import com.solodilov.feature_main_screen.presentation.delegateAdapter.model.ItemUi

open class BaseDiffUtilItemCallback : DiffUtil.ItemCallback<ItemUi>() {
  override fun areItemsTheSame(oldItem: ItemUi, newItem: ItemUi): Boolean =
    oldItem.itemId == newItem.itemId

  override fun areContentsTheSame(oldItem: ItemUi, newItem: ItemUi): Boolean {
    return oldItem.equals(newItem)
  }
}