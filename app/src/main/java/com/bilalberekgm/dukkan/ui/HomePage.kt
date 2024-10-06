package com.bilalberekgm.dukkan.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bilalberekgm.dukkan.DataSource
import com.bilalberekgm.dukkan.components.CustomBottomBar
import com.bilalberekgm.dukkan.components.CustomTopAppBar
import com.bilalberekgm.dukkan.components.SectionWithLazyRow
import com.bilalberekgm.dukkan.models.Shop
import com.bilalberekgm.dukkan.viewmodels.ShopViewModel

@Composable
fun HomePage(
    selectedIndex: Int,
    onBottomBarItemClicked: (Int) -> Unit,
    shopViewModel: ShopViewModel = hiltViewModel()
){
    var isSearchIconTapped by rememberSaveable {
        mutableStateOf(false)
    }

    val pagingData =  shopViewModel.pagingData

    Scaffold(
        topBar = { CustomTopAppBar(
            onSearchIconTapped = {
                    isSearchIconTapped = !isSearchIconTapped
            },
            onSearchTextEntered = {searchText ->
                Log.e("Control searchText", searchText)
                shopViewModel.updateSearchQuery(searchText)
            }
        )
                 },
        content = { padding ->
            if (!isSearchIconTapped) {
                Column(
                    modifier = Modifier
                        .padding(
                            top = padding.calculateTopPadding(),
                            start = 12.dp,
                            end = 12.dp,
                            bottom = 50.dp
                        )
                        .verticalScroll(rememberScrollState())
                ) {
                    repeat(5) {
                        SectionWithLazyRow(
                            title = "En çok satan Dükkanlar",
                            dataList = DataSource.getDataList()
                        ) {}
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
            else{
                filteredShopsPage(
                    pagingData = pagingData,
                    bottomPadding = 50.dp,
                    topPadding = padding.calculateTopPadding(),
                    )
            }
        },
        bottomBar = {
            CustomBottomBar(
                selectedIndex = selectedIndex,
                onBottomBarItemClicked = onBottomBarItemClicked
            )
        }
    )
}