package com.solodilov.feature_main_screen.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.solodilov.core.presentation.BaseViewModel
import com.solodilov.feature_main_screen.domain.usecase.GetCartSizeUseCase
import com.solodilov.feature_main_screen.domain.usecase.GetProductListUseCase
import com.solodilov.feature_main_screen.presentation.delegateAdapter.model.*
import com.solodilov.feature_main_screen.presentation.mapper.ProductUiMapper
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.RuntimeException
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val getProductListUseCase: GetProductListUseCase,
    private val getCartSizeUseCase: GetCartSizeUseCase,
    private val mapper: ProductUiMapper,
) : BaseViewModel() {

    private val _data = MutableLiveData<List<ItemUi>>()
    val data: LiveData<List<ItemUi>> = _data

    private val _cartSize = MutableLiveData<Int>(0)
    val cartSize: LiveData<Int> = _cartSize

    private val _category = MutableLiveData<Category>(Category.PHONES)
    val category: LiveData<Category> = _category

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    private var lastCategory: Category? = null

    init {
        loadProductList()
        getCartSize()
    }

    fun loadProductList() {
        _category.value?.let { currentCategory ->
            if (currentCategory != lastCategory) {
                lastCategory = currentCategory
                viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
                    _data.postValue(getLoaders())
                    delay(2000) //TODO remove after test
                    if (currentCategory == Category.PHONES) {
                        val items = getProductList()
                        _data.postValue(items)
                    } else {
                        throw RuntimeException()
                    }
                }
            }
        }
    }

    private fun getLoaders(): List<ItemUi> =
        listOf(
            HotSaleBlockUi(
                hotSaleList = listOf(ProgressHotSaleItem)
            ),
            BestSellerBlockUi(
                bestSellerList = (1..4).map { ProgressBestSellerItem }
            )
        )

    private suspend fun getProductList(): List<ItemUi> {
        val productList = getProductListUseCase()
        return listOf(
            HotSaleBlockUi(
                hotSaleList = productList.hotSaleList.map { hotSale ->
                    mapper.mapHotSaleToHotSaleUi(hotSale)
                }
            ),
            BestSellerBlockUi(
                bestSellerList = productList.bestSellerList.map { bestSeller ->
                    mapper.mapBestSellerToBestSellerUi(bestSeller)
                }
            )
        )
    }

    fun getCartSize() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val cartSize = getCartSizeUseCase()
            _cartSize.postValue(cartSize)
        }
    }

    fun setCategory(category: Category) {
        _category.value = category
    }

    private fun handleError(error: Throwable) {
        Log.d(TAG, "handleError: ${error.message}")
        _data.postValue(listOf(MainScreenErrorItem))
        lastCategory = null
    }

    companion object {
        const val TAG = "TAG"
    }
}