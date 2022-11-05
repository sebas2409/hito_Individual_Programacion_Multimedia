package com.watermelon.hitoindividualaccesodatos.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.watermelon.hitoindividualaccesodatos.home.data.network.dto.Deposit

@Composable
fun HomePage(homeViewModel: HomeViewModel, id: String) {
    val nombre = homeViewModel.nombre.collectAsState()
    val cc = homeViewModel.cc.collectAsState()
    val balance = homeViewModel.balance.collectAsState()
    val lista = homeViewModel.transactionList.collectAsState()
    val showDepositDialog = homeViewModel.showDepositDialog.collectAsState()
    val showWithdrawDialog = homeViewModel.showWithdrawDialog.collectAsState()
    val cantidad = homeViewModel.cantidad.collectAsState()
    homeViewModel.getInfo(id)
    homeViewModel.getTransacction(id)

    Scaffold(bottomBar = {
        BottomNavigation() {
            BottomNavigationItem(
                selected = false,
                onClick = { homeViewModel.handleDepositDialog(true) },
                icon = { Icon(Icons.Default.Add, contentDescription = "Add") },
                label = { Text(text = "Depositar") })
            BottomNavigationItem(
                selected = false,
                onClick = { homeViewModel.handleWithdrawDialog(true) },
                icon = { Icon(Icons.Default.Remove, contentDescription = "Remove") },
                label = { Text(text = "Retirar") })
        }
    }, content = {
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
                )
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(16.dp),
                elevation = 10.dp,
                shape = MaterialTheme.shapes.small,
                backgroundColor = Color(0xFFF5DD90)
            ) {
                Text(
                    text = nombre.value,
                    modifier = Modifier.padding(top = 16.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = balance.value.toString(),
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    color = Color(0xFFA31708),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 32.dp)
                )
                Text(
                    text = cc.value,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 75.dp)
                )
            }
            LazyColumn {
                items(lista.value) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, bottom = 5.dp)
                            .height(60.dp)
                    ) {
                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Column() {
                                Text(text = it.tipoTransaccion, fontWeight = FontWeight.Bold)
                                Text(text = it.timeStamp)
                            }
                            Column() {
                                Text(
                                    // estaria bien comprobar si es ingreso verde, retiro rojo
                                    text = it.importe.toString(),
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFA31708)
                                )
                                Text(text = "")
                            }
                        }
                    }
                }
            }
            if (showWithdrawDialog.value) {
                AlertDialog(
                    onDismissRequest = { homeViewModel.handleWithdrawDialog(false) },
                    title = { Text(text = "Ingrese la cantidad a retirar") },
                    text = {
                        OutlinedTextField(
                            value = cantidad.value,
                            onValueChange = { homeViewModel.handleCantidad(it) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            homeViewModel.withdraw(
                                Deposit(
                                    id,
                                    cantidad.value.toDouble()
                                )
                            )
                        }) {
                            Text(text = "Retirar")
                        }
                    })
            }
            if (showDepositDialog.value) {
                AlertDialog(
                    onDismissRequest = { homeViewModel.handleDepositDialog(false) },
                    title = { Text(text = "Ingrese la cantidad a depositar") },
                    text = {
                        OutlinedTextField(
                            value = cantidad.value,
                            onValueChange = { homeViewModel.handleCantidad(it) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            homeViewModel.deposit(
                                Deposit(
                                    id,
                                    cantidad.value.toDouble()
                                )
                            )
                        }) {
                            Text(text = "Depositar")
                        }
                    })
            }
        }
    })
}



