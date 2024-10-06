package com.bilalberekgm.dukkan.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bilalberekgm.dukkan.R
import com.bilalberekgm.dukkan.ui.theme.appNameColorPink


@Composable
fun CustomBottomBar(
    selectedIndex: Int,
    onBottomBarItemClicked: (Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(top = 12.dp ),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        BottomBarItem(
            iconResId = R.drawable.house_blank_24,
            label = "Ana sayfa",
            tintColor = if (selectedIndex == 0) appNameColorPink else Color.Black,
            onClick = {
                onBottomBarItemClicked.invoke(0)
            }
        )
        BottomBarItem(
            iconResId = R.drawable.heart_24,
            label = "Favorilerim",
            tintColor = if (selectedIndex == 1) appNameColorPink else Color.Black,
            onClick = {
                onBottomBarItemClicked.invoke(1)
            }
        )
        BottomBarItem(
            iconResId = R.drawable.basket_shopping_simple_24,
            label = "Sepetim",
            tintColor = if (selectedIndex == 2) appNameColorPink else Color.Black,
            onClick = {
                onBottomBarItemClicked.invoke(2)
            }
        )
        BottomBarItem(
            iconResId = R.drawable.category_alt_24,
            label = "Kategoriler",
            tintColor = if (selectedIndex == 3) appNameColorPink else Color.Black,
            onClick = {
                onBottomBarItemClicked.invoke(3)
            }
        )
    }
}


@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
fun CustomBottomBarPreview() {
    CustomBottomBar(onBottomBarItemClicked = {

    }, selectedIndex = 0)
}
