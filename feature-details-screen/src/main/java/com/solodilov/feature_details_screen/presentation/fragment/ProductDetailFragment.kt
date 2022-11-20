package com.solodilov.feature_details_screen.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.google.android.material.tabs.TabLayoutMediator
import com.solodilov.core.presentation.ViewModelFactory
import com.solodilov.core.presentation.viewBinding
import com.solodilov.feature_details_screen.R
import com.solodilov.feature_details_screen.databinding.FragmentProductDetailBinding
import com.solodilov.feature_details_screen.di.ProductDetailScreenComponentProvider
import com.solodilov.feature_details_screen.presentation.ProductDetailScreenState
import com.solodilov.feature_details_screen.presentation.ProductDetailViewModel
import com.solodilov.feature_details_screen.presentation.adapter.ImageSlideAdapter
import com.solodilov.feature_details_screen.presentation.adapter.ProductDetailPagerAdapter
import com.solodilov.feature_details_screen.presentation.adapter.model.ProductDetailTab
import java.util.*
import javax.inject.Inject
import kotlin.math.abs

class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private val binding by viewBinding(FragmentProductDetailBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: ProductDetailViewModel by viewModels { viewModelFactory }

    private var imageSlideAdapter: ImageSlideAdapter? = null
    private var productDetailPagerAdapter: ProductDetailPagerAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as ProductDetailScreenComponentProvider)
            .provideProductDetailScreenComponent()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeViewModel()
    }

    private fun initViews() {

        binding.backButton.setOnClickListener { findNavController().popBackStack() }
        binding.cartButton.setOnClickListener {
            findNavController().navigate(R.id.action_product_detail_fragment_to_cart_fragment)
        }

        binding.errorLayout.tryButton.setOnClickListener { viewModel.loadProductDetail() }
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner, ::processState)
        viewModel.isFavorite.observe(viewLifecycleOwner) { setImageForFavoriteButton(it) }
    }


    private fun processState(state: ProductDetailScreenState) {
        when (state) {
            is ProductDetailScreenState.Loading -> renderLoadingState()
            is ProductDetailScreenState.Content -> renderContentState(state)
            is ProductDetailScreenState.Error -> renderErrorState()
        }
    }

    private fun renderLoadingState() {
        toggleState(
            isLoading = true,
            isContent = false,
            isError = false,
        )
    }

    private fun renderContentState(contentState: ProductDetailScreenState.Content) {
        val data = contentState.content
        initSlider(data.images)
        with(binding) {
            productDetailName.text = data.title
            ratingBar.rating = data.rating.toFloat()
            favoriteButton.setOnClickListener { viewModel.toggleFavorite() }
            addToCart.text = String.format(
                Locale.ENGLISH,
                getString(R.string.add_to_cart_button),
                data.price.toFloat()
            )
        }
        initTabs()

        toggleState(
            isLoading = false,
            isContent = true,
            isError = false,
        )
    }


    private fun renderErrorState() {
        toggleState(
            isLoading = false,
            isContent = false,
            isError = true,
        )
    }

    private fun toggleState(isLoading: Boolean, isContent: Boolean, isError: Boolean) {
        binding.progressBar.isVisible = isLoading
        binding.productDetailContent.isVisible = isContent
        binding.errorLayout.root.isVisible = isError
    }

    private fun initSlider(images: List<String>) {
        imageSlideAdapter = ImageSlideAdapter(images)
        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
            addTransformer { page, position ->
                val r = 1 - abs(position)
                page.scaleY = 0.85f + r * 0.15f
            }
        }
        binding.imageSlider.apply {
            adapter = imageSlideAdapter
            offscreenPageLimit = 3
            setPageTransformer(compositePageTransformer)
        }
    }

    private fun setImageForFavoriteButton(isFavorite: Boolean) {
        val image = if (isFavorite) {
            R.drawable.ic_favorite_true
        } else {
            R.drawable.ic_favorite_false
        }
        binding.favoriteButton.setImageResource(image)
    }

    private fun initTabs() {
        productDetailPagerAdapter = ProductDetailPagerAdapter(this)
        with(binding) {
            productDetailTabContainer.adapter = productDetailPagerAdapter
            TabLayoutMediator(productDetailTabs, productDetailTabContainer) { tab, position ->
                tab.text = ProductDetailTab.values()[position].title
            }.attach()
        }
    }

    override fun onDestroyView() {
        imageSlideAdapter = null
        productDetailPagerAdapter = null
        super.onDestroyView()
    }
}