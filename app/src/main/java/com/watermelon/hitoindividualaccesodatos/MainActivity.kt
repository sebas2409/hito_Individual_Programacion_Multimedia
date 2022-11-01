package com.watermelon.hitoindividualaccesodatos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.watermelon.hitoindividualaccesodatos.home.ui.HomeViewModel
import com.watermelon.hitoindividualaccesodatos.login.ui.LoginViewModel
import com.watermelon.hitoindividualaccesodatos.navigation.MyNavHostController
import com.watermelon.hitoindividualaccesodatos.sign_up.ui.SignUpViewModel
import com.watermelon.hitoindividualaccesodatos.ui.theme.HitoIndividualAccesoDatosTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()
    private val signUpViewModel: SignUpViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HitoIndividualAccesoDatosTheme {

                val icon = R.drawable.logo
                val navHost = rememberNavController()
                MyNavHostController(
                    navHost = navHost,
                    icon = icon,
                    loginViewModel = loginViewModel,
                    signUpViewModel = signUpViewModel,
                    homeViewModel = homeViewModel
                )
            }
        }
    }
}

