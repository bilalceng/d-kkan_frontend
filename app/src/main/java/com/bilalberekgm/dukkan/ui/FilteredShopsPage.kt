package com.bilalberekgm.dukkan.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.bilalberekgm.dukkan.components.filterShopCard
import com.bilalberekgm.dukkan.models.Shop
import kotlinx.coroutines.flow.StateFlow

@Composable
fun filteredShopsPage(
    bottomPadding: Dp,
    topPadding:Dp,
    pagingData: StateFlow<PagingData<Shop>>
) {

    val lazyPagingItems = pagingData.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.padding(
            top = topPadding,
            bottom = bottomPadding
        )
    ) {
        items(lazyPagingItems.itemCount){ index ->
            val shop = lazyPagingItems[index]
            shop?.let {
                filterShopCard(
                    shopName = it.name,
                    categoryName = it.category
                )
            }
        }

    }

}


