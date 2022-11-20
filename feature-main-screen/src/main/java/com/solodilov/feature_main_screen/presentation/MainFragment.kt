package com.solodilov.feature_main_screen.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.solodilov.core.presentation.ViewModelFactory
import com.solodilov.core.presentation.viewBinding
import com.solodilov.feature_main_screen.R
import com.solodilov.feature_main_screen.databinding.FragmentMainBinding
import com.solodilov.feature_main_screen.databinding.ItemCategoryBinding
import com.solodilov.feature_main_screen.di.MainScreenComponentProvider
import com.solodilov.feature_main_screen.presentation.delegateAdapter.MainScreenAdapter
import com.solodilov.feature_main_screen.presentation.delegateAdapter.model.Category
import javax.inject.Inject

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: MainScreenViewModel by viewModels { viewModelFactory }

    private var adapter: MainScreenAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as MainScreenComponentProvider)
            .provideMainScreenComponent()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeViewModel()
    }

    private fun initViews() {
        binding.filter.setOnClickListener {
            findNavController().navigate(R.id.action_main_fragment_to_filter_options_fragment)
        }

        adapter = MainScreenAdapter(
            onProductClick = { startProductDetailFragment(it.itemId) },
            onRefreshClick = { refreshProductList() }
        )
        binding.productList.adapter = adapter

        initCategoryTabs()

        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.cart_fragment -> findNavController().navigate(R.id.cart_fragment)
            }
            true
        }
    }

    private fun observeViewModel() {
        viewModel.data.observe(viewLifecycleOwner) { adapter?.items = it }
        viewModel.cartSize.observe(viewLifecycleOwner) { setCartBadge(it) }
        viewModel.category.observe(viewLifecycleOwner) { checkSelectedTab(it) }
    }

    private fun initCategoryTabs() {
        Category.values().iterator().forEach { addTab(it) }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    viewModel.setCategory(Category.values()[it.position])
                    viewModel.loadProductList()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun addTab(category: Category) {

        val tabView = ItemCategoryBinding.inflate(
            LayoutInflater.from(binding.tabLayout.context),
            binding.tabLayout,
            false
        )
        tabView.categoryIcon.setBackgroundResource(category.icon)
        tabView.categoryName.text = category.title

        val newTab = binding.tabLayout.newTab()
        newTab.customView = tabView.root
        binding.tabLayout.addTab(newTab)
    }

    private fun checkSelectedTab(category: Category) {
        binding.tabLayout.getTabAt(category.ordinal)?.select()
    }

    private fun refreshProductList() {
        viewModel.loadProductList()
        viewModel.getCartSize()
    }

    private fun startProductDetailFragment(id: Long) {
        findNavController().navigate(R.id.action_main_fragment_to_product_detail_fragment)
    }

    private fun setCartBadge(count: Int) {
        if (count != 0) {
            val badge = binding.bottomNavigation.getOrCreateBadge(R.id.cart_fragment)
            badge.isVisible = true
            badge.number = count
        }
    }

    override fun onDestroyView() {
        adapter = null
        super.onDestroyView()
    }
}