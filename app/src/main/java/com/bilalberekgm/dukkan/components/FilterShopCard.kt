package com.bilalberekgm.dukkan.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bilalberekgm.dukkan.R

@Composable
fun filterShopCard(
    shopName: String = "Dükkan ismi",
    categoryName: String = "Kategori ismi"
) {

    val robotoNormal = FontFamily(
        Font(R.font.roboto_regular, FontWeight.Normal)
    )

    Row(
        modifier = Modifier
            .padding(12.dp)
            .height(80.dp)
            .fillMaxWidth()
            .background(Color.White)
            .border(
                border = BorderStroke(1.dp, Color.Gray),
                shape = RoundedCornerShape(12.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), // Add padding inside the Row if needed
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween // Adjusted to space between elements
        ) {
            Text(
                text = shopName,
                style = TextStyle(
                    fontFamily = robotoNormal,
                    fontSize = 18.sp
                )
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = categoryName,
                    style = TextStyle(
                        fontFamily = robotoNormal,
                        fontSize = 18.sp
                    )
                )
                Spacer(modifier = Modifier.width(10.dp))
                IconButton(onClick = { /* TODO: Handle click */ }) {
                    Icon(
                        modifier = Modifier.size(29.dp),
                        painter = painterResource(id = R.drawable.angle_small_right),
                        contentDescription = "Navigate"
                    )
                }
            }
        }
    }
}


@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun filterShopCardPreview() {
    filterShopCard()
}