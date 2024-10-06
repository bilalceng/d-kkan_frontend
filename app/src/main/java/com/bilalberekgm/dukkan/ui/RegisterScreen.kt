package com.bilalberekgm.dukkan.ui

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bilalberekgm.dukkan.R
import com.bilalberekgm.dukkan.ui.theme.blackWith70Opacity
import com.bilalberekgm.dukkan.utils.EmailValidator
import com.bilalberekgm.dukkan.utils.UserNameParser
import com.bilalberekgm.dukkan.utils.rememberImeState
import com.bilalberekgm.dukkan.viewmodels.RegisterViewModel
import dagger.hilt.android.qualifiers.ApplicationContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterPage(
    onRegisterButtonClickedTakeCredentials: (String, String, String, String) -> Unit,
    onGoToLoginPage: () -> Unit
) {

    val isPasswordShowed = remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    var password by remember { mutableStateOf(TextFieldValue("")) }
    var userName by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var isEmailError by remember { mutableStateOf(false) }
    var isPasswordError by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    val imeState = rememberImeState()

    LaunchedEffect(key1 = imeState.value){
        if(imeState.value){
            scrollState.scrollTo(scrollState.maxValue)
        }
    }

    val borderColor = if(isPasswordError) Color.Red else Color.Black
    val context  = LocalContext.current
    val robotoNormal = FontFamily(
        Font(R.font.roboto_regular, FontWeight.Normal)
    )
    val robotoSlab = FontFamily(
        Font(R.font.robotoslab_light, FontWeight.Light)
    )
    val loginText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = blackWith70Opacity,
                fontSize = 28.sp,
                fontFamily = robotoSlab
            )
        ) {
            append(text = stringResource(id = R.string.register_page_title))
        }
    }
    val loginInfo = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = blackWith70Opacity,
                fontSize = 17.sp,
                fontFamily = robotoNormal
            )
        ) {
            append(text = stringResource(id = R.string.register_page_info))
        }
    }
    val passwordTextFieldPlaceHolder = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = blackWith70Opacity,
                fontSize = 16.sp,
                fontFamily = robotoNormal
            )
        ) {
            append(text = stringResource(id = R.string.password_placeholder))
        }
    }
    val emailTextFieldPlaceHolder = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = blackWith70Opacity,
                fontSize = 16.sp,
                fontFamily = robotoNormal
            )
        ) {
            append(text = stringResource(id = R.string.email_placeholder))
        }
    }
    val userNameTextFieldPlaceHolder = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = blackWith70Opacity,
                fontSize = 16.sp,
                fontFamily = robotoNormal
            )
        ) {
            append(text = stringResource(id = R.string.username_placeholder))
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color.White)
            .padding(
                start = 24.dp,
                end = 24.dp,
                top = 60.dp,
                bottom = 30.dp
            ),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Column {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                painter = painterResource(id = R.drawable.pablita_964),
                contentDescription = "login"
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = loginText,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = loginInfo,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(18.dp))
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    isPasswordError = it.text.length < 8
                },
                label = { Text(passwordTextFieldPlaceHolder) },
                isError = isPasswordError,
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.lock_24),
                        contentDescription = null,
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            isPasswordShowed.value = !isPasswordShowed.value
                        }
                    ) {
                        Icon(
                            painter = painterResource(
                                id = if (isPasswordShowed.value) {
                                    R.drawable.eye_24
                                } else {
                                    R.drawable.eye_crossed_24
                                }
                            ),
                            contentDescription = stringResource(id = R.string.toggle_password_visibility_description)
                        )
                    }
                },
                visualTransformation = if (isPasswordShowed.value) {
                    androidx.compose.ui.text.input.VisualTransformation.None
                } else {
                    androidx.compose.ui.text.input.PasswordVisualTransformation()
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                ),
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(8.dp)),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = Color.Black,
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = userName,
                onValueChange = { userName = it },
                label = { Text(userNameTextFieldPlaceHolder) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.user_24),
                        contentDescription = null,
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                ),
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(8.dp)),
                shape = RoundedCornerShape(8.dp),
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    isEmailError = !EmailValidator.isValidEmail(it.text)
                },
                isError = isEmailError,
                label = { Text(emailTextFieldPlaceHolder) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.envelope_24),
                        contentDescription = null,
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                ),
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(8.dp)),
                shape = RoundedCornerShape(8.dp),
            )
        }

        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    val namePair: Pair<String, String> =  UserNameParser.parseName(userName.text)
                    if (namePair.first.isNotEmpty()
                        &&
                        namePair.second.isNotEmpty()
                        &&
                        EmailValidator.isValidEmail(email.text)
                        &&
                        password.text.length >= 8
                        ){
                           onRegisterButtonClickedTakeCredentials.invoke(
                               namePair.first,
                               namePair.second,
                               email.text.toString(),
                               password.text.toString()
                           )
                          }else{
                        Toast.makeText(context, "Lütfen geçerli değerler giriniz", Toast.LENGTH_SHORT).show()
                    }
                          },
                modifier = Modifier
                    .fillMaxWidth(0.90f)
            ) {
                Text(text = stringResource(id = R.string.register_button_text))
            }
            Spacer(modifier = Modifier.height(8.dp))
            TextButton(
                onClick = { onGoToLoginPage.invoke() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.already_have_account_text))
            }
        }
    }
}


