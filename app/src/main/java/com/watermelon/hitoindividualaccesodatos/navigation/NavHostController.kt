package com.watermelon.hitoindividualaccesodatos.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.watermelon.hitoindividualaccesodatos.home.ui.HomePage
import com.watermelon.hitoindividualaccesodatos.home.ui.HomeViewModel
import com.watermelon.hitoindividualaccesodatos.login.ui.LoginPage
import com.watermelon.hitoindividualaccesodatos.login.ui.LoginViewModel
import com.watermelon.hitoindividualaccesodatos.sign_up.ui.SignUpPage
import com.watermelon.hitoindividualaccesodatos.sign_up.ui.SignUpViewModel

@Composable
fun MyNavHostController(
    navHost: NavHostController,
    icon: Int,
    loginViewModel: LoginViewModel,
    signUpViewModel: SignUpViewModel,
    homeViewModel: HomeViewModel
) {

    NavHost(navController = navHost, startDestination = Routes.Login.route) {
        composable(Routes.Login.route) {
            LoginPage(
                icon = icon,
                loginViewModel = loginViewModel,
                navHostController = navHost
            )
        }
        composable(Routes.SignUp.route) {
            SignUpPage(signUpViewModel = signUpViewModel, navController = navHost)
        }
        composable(Routes.Home.route){
            HomePage(homeViewModel)
        }
    }
}