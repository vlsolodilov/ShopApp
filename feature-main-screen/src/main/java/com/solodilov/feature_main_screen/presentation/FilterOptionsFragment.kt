package com.solodilov.feature_main_screen.presentation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.solodilov.core.presentation.viewBinding
import com.solodilov.feature_main_screen.R
import com.solodilov.feature_main_screen.databinding.BottomSheetFilterBinding


class FilterOptionsFragment : BottomSheetDialogFragment() {

    private val binding: BottomSheetFilterBinding by viewBinding { BottomSheetFilterBinding.bind(it) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? =
        inflater.inflate(R.layout.bottom_sheet_filter, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    override fun getTheme() = com.solodilov.core.R.style.CustomBottomSheetDialogTheme

    private fun initViews() {
        initSpinners()
        binding.closeFilter.setOnClickListener { dismiss() }
        binding.applyFilter.setOnClickListener { dismiss() }
    }

    private fun initSpinners() {

        val brandAdapter = ArrayAdapter<String>(
            requireContext(),
            R.layout.custom_spinner,
            resources.getStringArray(R.array.phone_brand))
        brandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.brandSpinner.adapter = brandAdapter

        val priceAdapter = ArrayAdapter<String>(
            requireContext(),
            R.layout.custom_spinner,
            resources.getStringArray(R.array.phone_price))
        priceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.priceSpinner.adapter = priceAdapter

        val sizeAdapter = ArrayAdapter<String>(
            requireContext(),
            R.layout.custom_spinner,
            resources.getStringArray(R.array.phone_size))
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.sizeSpinner.adapter = sizeAdapter
    }

}


