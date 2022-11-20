package com.solodilov.feature_details_screen.presentation.fragment

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.chip.Chip
import com.solodilov.core.presentation.ViewModelFactory
import com.solodilov.core.presentation.viewBinding
import com.solodilov.feature_details_screen.R
import com.solodilov.feature_details_screen.databinding.FragmentShopBinding
import com.solodilov.feature_details_screen.di.ProductDetailScreenComponentProvider
import com.solodilov.feature_details_screen.presentation.ProductDetailScreenState
import com.solodilov.feature_details_screen.presentation.ProductDetailViewModel
import javax.inject.Inject

class ShopFragment : Fragment(R.layout.fragment_shop) {

    private val binding by viewBinding(FragmentShopBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: ProductDetailViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    ) { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as ProductDetailScreenComponentProvider)
            .provideProductDetailScreenComponent()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner, ::renderState)
        viewModel.productColor.observe(viewLifecycleOwner) {checkColorButton(it)}
        viewModel.productCapacity.observe(viewLifecycleOwner) {checkCapacityChip(it)}
    }

    private fun renderState(state: ProductDetailScreenState) {
        if (state is ProductDetailScreenState.Content) {
            val data = state.content
            with(binding) {
                cpuName.text = data.cpu
                cameraName.text = data.camera
                ssdName.text = data.ssd
                sdName.text = data.sd
            }

            data.color.forEach { addColorButton(it) }
            data.capacity.forEach { addCapacityChip(it) }
        }
    }

    private fun addColorButton(color: String) {
        val button = layoutInflater.inflate(
            R.layout.color_picker_button,
            binding.colorPicker,
            false
        ) as RadioButton
        button.backgroundTintList = ColorStateList(
            arrayOf(intArrayOf()),
            intArrayOf(Color.parseColor(color))
        )
        button.setOnClickListener { viewModel.setProductColor(color) }
        button.tag = color
        binding.colorPicker.addView(button)
    }

    private fun checkColorButton(color: String) {
        binding.colorPicker.children.forEach {
            val button = it as RadioButton
            button.isChecked = button.tag == color
        }
    }

    private fun addCapacityChip(name: String) {
        val chip = layoutInflater.inflate(
            R.layout.capacity_chip,
            binding.capacityChips,
            false
        ) as Chip
        chip.text = getString(R.string.capacity_format, name)
        chip.tag = name
        chip.setOnClickListener { viewModel.setProductCapacity(name) }
        binding.capacityChips.addView(chip)
    }

    private fun checkCapacityChip(name: String) {
        binding.capacityChips.children.forEach {
            val chip = it as Chip
            chip.isChecked = chip.tag == name
        }
    }
}