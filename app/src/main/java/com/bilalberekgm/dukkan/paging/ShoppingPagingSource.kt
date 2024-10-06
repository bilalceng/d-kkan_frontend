package com.bilalberekgm.dukkan.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bilalberekgm.dukkan.models.Shop
import com.bilalberekgm.dukkan.service.ApiService

class ShoppingPagingSource(
    private val apiService: ApiService,
    private val name:String
): PagingSource<Int, Shop>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Shop> {
        return try {
            val page = params.key ?: 0
            val response = apiService.searchShops(name,page, params.loadSize)

            Log.e("Control searchText", response.code().toString())

            var shops:List<Shop> = ArrayList();

            if(response.isSuccessful){
                val body = response.body()
                if(body != null){
                    shops = body.content
                }
            }

            LoadResult.Page(
                data = shops,
                // Because spring boot pageable object is zero based.
                prevKey =  if (page == 0) null else page - 1,
                nextKey = if (shops.isEmpty()) null else page + 1
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Shop>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
                val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}