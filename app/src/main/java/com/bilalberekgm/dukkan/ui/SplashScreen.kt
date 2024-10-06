package com.bilalberekgm.dukkan.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bilalberekgm.dukkan.R
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bilalberekgm.dukkan.ui.theme.appNameColorPink
import kotlinx.coroutines.delay
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.compose.ui.draw.alpha


@Composable
fun SplashScreen(onTimeOut: () -> Unit) {
    val uniqaOne = FontFamily(
        Font(R.font.unicaone_regular, FontWeight.Light)
    )
    val robotoNormal = FontFamily(
        Font(R.font.roboto_regular, FontWeight.Normal)
    )

    var textVisible by remember { mutableStateOf(true) }
    val alphaAnim by animateFloatAsState(
        targetValue = if (textVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 1000), label = ""
    )

    // Control visibility state
    LaunchedEffect(Unit) {
        delay(1000)
        textVisible = false
        delay(1000) // Wait for fade-out animation to complete
        onTimeOut()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .alpha(alphaAnim) // Apply animation
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Dükkan",
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                fontFamily = uniqaOne,
                color = appNameColorPink
            )
            Spacer(Modifier.height(12.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Güvenli alışverişin adresi",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontFamily = robotoNormal
            )
        }
    }
}



@Composable
@Preview(
    showBackground = true,
    showSystemUi = true,
)
fun SplashScreenPreview(){
    SplashScreen {

    }
}