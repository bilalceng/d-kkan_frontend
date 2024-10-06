package com.bilalberekgm.dukkan.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.bilalberekgm.dukkan.models.Shop
import com.bilalberekgm.dukkan.paging.ShoppingPagingSource
import com.bilalberekgm.dukkan.service.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShopRepository @Inject constructor (
    private val apiService: ApiService
){
    fun getFilteredShops(name: String): Pager<Int,Shop>{
        return Pager(
            config = PagingConfig(
                pageSize = 3,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ShoppingPagingSource(apiService = apiService, name =name )}
        )
    }
}