package com.solodilov.feature_cart_screen.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.solodilov.core.presentation.ViewModelFactory
import com.solodilov.core.presentation.viewBinding
import com.solodilov.feature_cart_screen.R
import com.solodilov.feature_cart_screen.databinding.FragmentCartBinding
import com.solodilov.feature_cart_screen.di.CartScreenComponentProvider
import java.util.*
import javax.inject.Inject

class CartFragment : Fragment(R.layout.fragment_cart) {

    private val binding by viewBinding(FragmentCartBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: CartViewModel by viewModels { viewModelFactory }

    private var cartProductAdapter: CartProductAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as CartScreenComponentProvider)
            .provideCartScreenComponent()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeViewModel()
    }

    private fun initViews() {
        cartProductAdapter = CartProductAdapter()
        binding.cartProductList.adapter = cartProductAdapter

        binding.backButton.setOnClickListener { findNavController().popBackStack() }

        binding.errorLayout.tryButton.setOnClickListener { viewModel.loadCartProductList() }
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner, ::processState)
    }


    private fun processState(state: CartScreenState) {
        when (state) {
            is CartScreenState.Loading -> renderLoadingState()
            is CartScreenState.Content -> renderContentState(state)
            is CartScreenState.Error -> renderErrorState()
        }
    }

    private fun renderLoadingState() {
        toggleState(
            isLoading = true,
            isContent = false,
            isError = false,
        )
    }

    private fun renderContentState(contentState: CartScreenState.Content) {
        if (contentState.content.cartProductList.isNotEmpty()) {
            cartProductAdapter?.submitList(contentState.content.cartProductList)
            binding.totalPrice.text = String.format(
                Locale.ENGLISH,
                getString(R.string.total_price_format),
                contentState.content.total
            )
            binding.deliveryCost.text = contentState.content.delivery
            toggleState(
                isLoading = false,
                isContent = true,
                isError = false,
            )
            binding.emptyCart.isVisible = false
        } else {
            toggleState(
                isLoading = false,
                isContent = false,
                isError = false,
            )
            binding.emptyCart.isVisible = true
        }

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
        binding.cartContent.isVisible = isContent
        binding.errorLayout.root.isVisible = isError
    }

    override fun onDestroyView() {
        cartProductAdapter = null
        super.onDestroyView()
    }
}