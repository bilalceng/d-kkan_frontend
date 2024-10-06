package com.bilalberekgm.dukkan.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bilalberekgm.dukkan.models.Shop
import com.bilalberekgm.dukkan.repositories.ShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val shopRepository: ShopRepository
): ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText

    fun updateSearchQuery(newQuery: String) {
        _searchText.value = newQuery
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun getPagingDataFlow(): Flow<PagingData<Shop>> {
        return searchText
            .flatMapLatest { query ->
                fetchShops(query)
            }
            .cachedIn(viewModelScope)
    }


    private fun fetchShops(query: String): Flow<PagingData<Shop>> {
        return shopRepository.getFilteredShops(query).flow
    }

    val pagingData: StateFlow<PagingData<Shop>> = getPagingDataFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            PagingData.empty()
        )
}
