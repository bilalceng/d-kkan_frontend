package com.bilalberekgm.dukkan.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.bilalberekgm.dukkan.R
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.bilalberekgm.dukkan.ui.theme.appNameColorPink


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
     onSearchIconTapped:() -> Unit = {},
     onSearchTextEntered:(String) -> Unit = {}
){
    val uniqaOne = FontFamily(
        Font(R.font.unicaone_regular, FontWeight.Light)
    )
    val focusManager = LocalFocusManager.current
    val robotoNormal = FontFamily(
        Font(R.font.roboto_regular, FontWeight.Normal)
    )
    val welcomeAndBrandText = buildAnnotatedString {

        withStyle(style =  SpanStyle(fontSize = 28.sp, color = appNameColorPink, fontFamily = uniqaOne )){
            append("Dükkan")
        }
    }
    var isSearchBarVisible by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
   TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                AnimatedVisibility(
                    visible = isSearchBarVisible,
                    enter = expandHorizontally(),
                    exit = shrinkHorizontally()
                ) {
                    OutlinedTextField(
                        value = searchText,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done // Show "Done" action on the keyboard
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.clearFocus()// Unfocus the text field when "Done" is pressed
                            }
                        ),

                        maxLines = 1,
                        onValueChange = {
                                        searchText = it
                                        onSearchTextEntered.invoke(it.text)
                                        },
                        placeholder = { Text("Ürün filtrele...") },
                        textStyle = TextStyle(fontSize = 16.sp, fontFamily = robotoNormal),
                        modifier = Modifier
                            .weight(1f)
                            .background(Color.White)
                    )
                }

                if (!isSearchBarVisible) {
                    Text(
                        text =welcomeAndBrandText ,
                        modifier = Modifier.weight(1f)
                    )
                }

                IconButton(
                    onClick = {
                        isSearchBarVisible = !isSearchBarVisible
                        onSearchIconTapped.invoke()
                    }
                ) {
                    Icon(
                        painter =  painterResource(id = if(isSearchBarVisible) R.drawable.circle_xmark_24 else R.drawable.search_24),
                        contentDescription = "Search"
                    )
                }
                IconButton(onClick = { /* Handle person icon click */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.user_24),
                        contentDescription = "Person"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black,
            actionIconContentColor = Color.Black
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun CustomTopAppBarPreview() {
 CustomTopAppBar(
     onSearchIconTapped = {},
     onSearchTextEntered = { }
 )
}
