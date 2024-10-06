package com.bilalberekgm.dukkan.navigation


import Favourites
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bilalberekgm.dukkan.ui.Buckets
import com.bilalberekgm.dukkan.ui.Categories
import com.bilalberekgm.dukkan.ui.HomePage
import com.bilalberekgm.dukkan.ui.LoginPage
import com.bilalberekgm.dukkan.ui.RegisterPage
import com.bilalberekgm.dukkan.ui.SplashScreen
import com.bilalberekgm.dukkan.ui.WelcomeScreen
import com.bilalberekgm.dukkan.utils.JwtTokenParser
import com.bilalberekgm.dukkan.utils.Routes
import com.bilalberekgm.dukkan.viewmodels.RegisterViewModel
import com.bilalberekgm.dukkan.viewmodels.SessionViewModel
import kotlin.math.log

@Composable
fun Navigation(
    sessionViewModel: SessionViewModel = hiltViewModel(),
    registerViewModel: RegisterViewModel = hiltViewModel()
) {
    var result by rememberSaveable {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    val registerState  = registerViewModel.registerState.collectAsState()
    val isErrorOccurred  = registerViewModel.isErrorOccurredState.collectAsState()
    val messageState = registerViewModel.errorMessageState.collectAsState()
    val navController = rememberNavController()
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
    val token by sessionViewModel.jwt.collectAsState("")

    token?.let {
        if (it.isNotEmpty()) {
            result = JwtTokenParser.parseJwtToken(token!!)
        }
    }


    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH_PAGE.name,
    ) {
        composable(route = Routes.SPLASH_PAGE.name) {
            SplashScreen {
                if (result) {
                    navController.navigate(Routes.HOME_PAGE.name) {
                        popUpTo(Routes.SPLASH_PAGE.name) { inclusive = true }
                    }
                } else {
                    navController.navigate(Routes.WELCOME_PAGE.name) {
                        popUpTo(Routes.SPLASH_PAGE.name) { inclusive = true }
                    }
                }
            }
        }
        composable(route = Routes.WELCOME_PAGE.name) {
            WelcomeScreen(
                onLoginButtonClicked = {
                    navController.navigate(Routes.LOGIN_PAGE.name)
                },
                onRegisterButtonClicked = {
                    navController.navigate(Routes.REGISTER_PAGE.name)
                }
            )
        }
        composable(route = Routes.LOGIN_PAGE.name) {
            LoginPage{
                navController.navigate(Routes.HOME_PAGE.name){
                    popUpTo(0){
                        inclusive = true
                    }
                }
            }
        }
        composable(route = Routes.REGISTER_PAGE.name) {
            RegisterPage(
                onRegisterButtonClickedTakeCredentials = { firstname: String, lastname: String, email: String, password: String ->
                    sessionViewModel.apply {
                        saveUserFirstName(firstname)
                        saveUserLastName(lastname)
                        saveEmail(email)
                        saveUserPassword(password)
                    }
                    Log.d("CONTROL NAVIGATION", "Navigation button click: $messageState")
                    registerViewModel.registerUser(
                        firstName = firstname,
                        lastName = lastname,
                        email = email,
                        password = password
                    )
                }
            ){
                navController.navigate(Routes.LOGIN_PAGE.name){

                }
            }

            LaunchedEffect(registerState.value, isErrorOccurred.value) {
                registerState.value?.let { response ->
                    if (!isErrorOccurred.value) {
                        sessionViewModel.apply {
                            saveUserRole(response.role)
                            saveUserJwtToken(response.token)
                        }
                        navController.navigate(Routes.HOME_PAGE.name) {
                            popUpTo(0) { inclusive = true }
                        }
                        registerViewModel.resetStates()
                    }
                }
            }
            if (messageState.value.isNotEmpty()) {
                Toast.makeText(context, messageState.value, Toast.LENGTH_SHORT).show()
                registerViewModel.resetErrorState()
            }
        }

        composable(route = Routes.HOME_PAGE.name) {
            HomePage(
                selectedIndex = selectedIndex,
                onBottomBarItemClicked = { index ->
                    selectedIndex = index
                    bottomNavigationDecider(navController = navController, index = index)
                }
            )
        }
        composable(route = Routes.FAVOURITES_PAGE.name) {
            Favourites(
                selectedIndex = selectedIndex,
                onBottomBarItemClicked = { index ->
                    selectedIndex = index
                    bottomNavigationDecider(navController = navController, index = index)
                }
            )
        }
        composable(route = Routes.MY_BUCKET_PAGE.name) {
            Buckets(
                selectedIndex = selectedIndex,
                onBottomBarItemClicked = { index ->
                    selectedIndex = index
                    bottomNavigationDecider(navController = navController, index = index)
                }
            )
        }
        composable(route = Routes.CATEGORIES_PAGE.name) {
            Categories(
                selectedIndex = selectedIndex,
                onBottomBarItemClicked = { index ->
                    selectedIndex = index
                    bottomNavigationDecider(navController = navController, index = index)
                }
            )
        }
    }
}



fun bottomNavigationDecider(navController: NavHostController, index: Int) {
    when (index) {
        0 -> {
            navController.navigate(Routes.HOME_PAGE.name) {
                launchSingleTop = true
                restoreState = true
                popUpTo(Routes.HOME_PAGE.name)
            }

        }

        1 -> {
            navController.navigate(Routes.FAVOURITES_PAGE.name) {
                launchSingleTop = true
                restoreState = true
                popUpTo(Routes.HOME_PAGE.name)
            }

        }

        2 -> {
            navController.navigate(Routes.MY_BUCKET_PAGE.name) {
                launchSingleTop = true
                restoreState = true
                popUpTo(Routes.HOME_PAGE.name)
            }
        }

        3 -> {
            navController.navigate(Routes.CATEGORIES_PAGE.name) {
                launchSingleTop = true
                restoreState = true
                popUpTo(Routes.HOME_PAGE.name)
            }
        }
    }
}