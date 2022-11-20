package com.solodilov.feature_details_screen.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.solodilov.core.presentation.BaseViewModel
import com.solodilov.feature_details_screen.domain.usecase.GetProductDetailUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductDetailViewModel @Inject constructor(
    private val getProductDetailUseCase: GetProductDetailUseCase,
) : BaseViewModel() {

    private val _state = MutableLiveData<ProductDetailScreenState>(ProductDetailScreenState.Loading)
    val state: LiveData<ProductDetailScreenState> = _state

    private val _productColor = MutableLiveData<String>()
    val productColor: LiveData<String> = _productColor

    private val _productCapacity = MutableLiveData<String>()
    val productCapacity: LiveData<String> = _productCapacity

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    init {
        loadProductDetail()
    }

    fun loadProductDetail() {
        _state.value = ProductDetailScreenState.Loading
        Log.d(TAG, "loadProductDetail: ")
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val content = getProductDetailUseCase()
            _state.postValue(ProductDetailScreenState.Content(content = content))
            _isFavorite.postValue(content.isFavorites)
            _productColor.postValue(content.color.firstOrNull())
            _productCapacity.postValue(content.capacity.firstOrNull())
        }
    }

    fun toggleFavorite() =
        _isFavorite.value?.let {
            _isFavorite.value = !it
        }

    fun setProductColor(color: String) {
        _productColor.value = color
    }

    fun setProductCapacity(capacity: String) {
        _productCapacity.value = capacity
    }

    private fun handleError(error: Throwable) {
        Log.d(TAG, "handleError: ${error.message}")
        _state.postValue(ProductDetailScreenState.Error)
    }

    companion object {
        const val TAG = "TAG"
    }
}