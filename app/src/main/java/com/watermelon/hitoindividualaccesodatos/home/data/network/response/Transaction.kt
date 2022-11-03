package com.watermelon.hitoindividualaccesodatos.home.data.network.response

data class Transaction(
    val id: String,
    val idCliente: String,
    val tipoTransaccion: String,
    val importe: Double,
    val timeStamp: String
)
