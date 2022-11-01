package com.watermelon.hitoindividualaccesodatos.sign_up.data.network.response

data class AccountResponse(
    val id: String,
    val nombre: String,
    val dni: String,
    val cc: String,
    val balance: Double,
    val fechaCreacion: String
)
