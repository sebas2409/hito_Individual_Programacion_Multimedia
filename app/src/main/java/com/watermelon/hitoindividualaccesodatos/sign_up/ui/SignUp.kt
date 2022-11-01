package com.watermelon.hitoindividualaccesodatos.sign_up.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun SignUpPage(signUpViewModel: SignUpViewModel, navController: NavHostController) {

    val show = signUpViewModel.show.collectAsState(initial = false)
    val nombre = signUpViewModel.nombre.collectAsState(initial = "")
    val dni = signUpViewModel.dni.collectAsState(initial = "")
    val saldo = signUpViewModel.saldo.collectAsState(initial = "")
    val id = signUpViewModel.id.collectAsState(initial = "")

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
        Text(
            text = "Si te creas una cuenta te regalamos 100€",
            fontSize = 20.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        OutlinedTextField(
            value = nombre.value,
            onValueChange = { signUpViewModel.handleName(it) },
            label = { Text(text = "Nombre", color = Color.LightGray) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFFF76C5E),
                textColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        OutlinedTextField(
            value = dni.value,
            onValueChange = { signUpViewModel.handleDni(it) },
            label = { Text(text = "Dni", color = Color.LightGray) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFFF76C5E),
                textColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        OutlinedTextField(
            value = saldo.value,
            onValueChange = { signUpViewModel.handleSaldo(it) },
            label = { Text(text = "Saldo Inicial", color = Color.LightGray) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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
                signUpViewModel.createAccount(nombre.value,dni.value,saldo.value)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Crear cuenta")
        }
        Text(
            text = "Iniciar sesión",
            modifier = Modifier
                .clickable {
                    navController.navigate("login")
                }
                .padding(16.dp),
            fontSize = 14.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        if (show.value) {
            MyAlertDialog(viewModel = signUpViewModel, id.value)
        }
    }
}

@Composable
fun MyAlertDialog(viewModel: SignUpViewModel, id: String) {
    val clipboardManager = LocalClipboardManager.current
    AlertDialog(
        onDismissRequest = { viewModel.handleShow(false) },
        title = { Text(text = "Copia el id para poder acceder!") },
        text = {
            OutlinedTextField(
                value = id,
                onValueChange = {},
                readOnly = true,
            )
        },
        confirmButton = {
            TextButton(onClick = {
                viewModel.copyToClipBoard(clipboardManager, id = id)
            }) {
                Text(text = "Copiar")
            }
        })
}