package com.bilalberekgm.dukkan.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bilalberekgm.dukkan.R
import com.bilalberekgm.dukkan.models.DukkanData
import com.bilalberekgm.dukkan.ui.theme.blackWith70Opacity
import com.bilalberekgm.dukkan.ui.theme.greenWith60Opacity

@Composable
fun  DukkanCard(
    dukkanData: DukkanData,
    onCardClicked: () -> Unit
) {

    val closedAtOpenedAt = buildAnnotatedString {
        withStyle(style = SpanStyle(fontSize = 14.sp, color = Color.White)){
            append(dukkanData.openAt)
            append(" - ")
            append(dukkanData.closedAt)
        }
    }
    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(150.dp)
            .width(200.dp)
            .clickable {
                onCardClicked.invoke()
            },
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(width = 1.dp, color = blackWith70Opacity)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = dukkanData.imageId ),
                contentDescription = "dukkan card"
            )

            Column (
                modifier = Modifier
                    .background(color = greenWith60Opacity)
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(8.dp)
                    .align(Alignment.BottomCenter)



            ){
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = dukkanData.title,
                    style = TextStyle(fontSize = 20.sp),
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.align(Alignment.End),
                    text = closedAtOpenedAt
                )
            }
        }
    }
}

