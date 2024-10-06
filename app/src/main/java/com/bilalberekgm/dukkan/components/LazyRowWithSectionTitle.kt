package com.bilalberekgm.dukkan.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bilalberekgm.dukkan.models.DukkanData

@Composable
fun SectionWithLazyRow(
    title: String,
    dataList: List<DukkanData>,
    onItemClick: (DukkanData) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(vertical = 12.dp)
    ) {
        Text(
            modifier = Modifier.padding(start = 7.dp),
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow {
            itemsIndexed(dataList) { _, item ->
                DukkanCard(dukkanData = item, onCardClicked = {onItemClick(item)} )
            }
        }
    }
}
