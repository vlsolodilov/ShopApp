package com.solodilov.feature_details_screen.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.solodilov.feature_details_screen.presentation.adapter.model.ProductDetailTab
import com.solodilov.feature_details_screen.presentation.fragment.EmptyFragment
import com.solodilov.feature_details_screen.presentation.fragment.ShopFragment

class ProductDetailPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int =
        ProductDetailTab.values().size

    override fun createFragment(position: Int): Fragment =
        when (ProductDetailTab.values()[position]) {
            ProductDetailTab.SHOP -> ShopFragment()
            else -> EmptyFragment()
        }

}