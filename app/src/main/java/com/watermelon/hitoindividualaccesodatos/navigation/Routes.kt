package com.watermelon.hitoindividualaccesodatos.navigation

sealed class Routes(val route: String) {
    object Login : Routes("login")
    object SignUp : Routes("signup")
    object Home : Routes("home")

}
