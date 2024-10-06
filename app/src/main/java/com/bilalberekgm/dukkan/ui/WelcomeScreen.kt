package com.bilalberekgm.dukkan.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bilalberekgm.dukkan.R
import com.bilalberekgm.dukkan.ui.theme.appNameColorPink
import com.bilalberekgm.dukkan.ui.theme.blackWith70Opacity

@Composable
fun WelcomeScreen(
    onLoginButtonClicked: () -> Unit,
    onRegisterButtonClicked: () -> Unit
) {
    val uniqaOne = FontFamily(
        Font(R.font.unicaone_regular, FontWeight.Light)
    )
    val robotoNormal = FontFamily(
        Font(R.font.roboto_regular, FontWeight.Normal)
    )
    val robotoSlab = FontFamily(
        Font(R.font.robotoslab_light, FontWeight.Light)
    )
    val welcomeAndBrandText = buildAnnotatedString {
        withStyle(style =  SpanStyle(fontSize = 28.sp, color = appNameColorPink, fontFamily = uniqaOne )){
            append("Dükkan")
        }
        withStyle(style =  SpanStyle(fontSize = 28.sp, color = blackWith70Opacity)){
            append(" ")
        }
        withStyle(style =  SpanStyle(fontSize = 28.sp, color = blackWith70Opacity, fontFamily = robotoSlab)){
            append("'a Hoşgeldin")
        }
    }

    val subWelcomeText = buildAnnotatedString {
        withStyle(style = SpanStyle(fontSize = 17.sp, fontFamily = robotoNormal)){
            append("\n" +
                    "The sun dipped below the horizon, painting the sky with hues of orange and pink.")
        }

    }

    Column(
        modifier = Modifier
            .padding(
                start = 24.dp,
                end = 24.dp,
                top = 60.dp,
                bottom = 60.dp
            )
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ){
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                painter = painterResource(id = R.drawable.urban_892),
                contentDescription = "shopping"
            )
        }

        Column{
            Text(modifier = Modifier
                    .fillMaxWidth(),
                text = welcomeAndBrandText,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = subWelcomeText,
                textAlign = TextAlign.Center
                )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth() // Ensure Column takes the full width
        ) {
            Button(
                onClick = { onLoginButtonClicked.invoke() },
                modifier = Modifier
                    .fillMaxWidth(0.90f) // Button takes 90% of the column's width
            ) {
                Text(text = "Giriş Yap")
            }
            Spacer(modifier = Modifier.height(8.dp)) // Add spacing between buttons if needed
            OutlinedButton(
                onClick = { onRegisterButtonClicked.invoke() },
                modifier = Modifier
                    .fillMaxWidth(0.90f) // Button takes 90% of the column's width
            ) {
                Text(text ="Kayıt Ol")
            }
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
fun WelcomeScreenPreview(){
    WelcomeScreen(
        onLoginButtonClicked = {},
        onRegisterButtonClicked = {}
    )
}
