package com.bilalberekgm.dukkan.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun BottomBarItem(
    iconResId: Int,
    label: String,
    tintColor: Color = Color.Black,
    onClick: () -> Unit
) {

        Column(
            modifier = Modifier
                .clickable(
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                    indication = null, // Removes the ripple effect
                    onClick = onClick
                ).
            wrapContentSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = label,
                tint = tintColor
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = label,
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
                color = tintColor
            )
        }
    }
