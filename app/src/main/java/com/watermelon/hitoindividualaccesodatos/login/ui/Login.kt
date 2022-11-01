package com.watermelon.hitoindividualaccesodatos.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.watermelon.hitoindividualaccesodatos.navigation.Routes

@Composable
fun LoginPage(icon: Int, loginViewModel: LoginViewModel, navHostController: NavHostController) {

    val id = loginViewModel.id.collectAsState(initial = "")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF586BA4),
                        Color(0xFF324376)
                    )
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = "Logo",
            modifier = Modifier
                .padding(bottom = 50.dp)
                .height(120.dp)
        )
        OutlinedTextField(
            value = id.value,
            onValueChange = { loginViewModel.handleId(it) },
            label = { Text(text = "Id", color = Color.LightGray) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFFF76C5E),
                textColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Button(
            onClick = {
                //todo -> comprobar que el id existe en bbdd
                navHostController.navigate(Routes.Home.route)
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Ingresar")
        }
        Text(
            text = "¿Aún no tienes una cuenta?",
            modifier = Modifier
                .clickable {
                    navHostController.navigate(Routes.SignUp.route)
                }
                .padding(16.dp),
            fontSize = 14.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}
