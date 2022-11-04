package com.watermelon.hitoindividualaccesodatos.home.service

import com.watermelon.hitoindividualaccesodatos.home.data.network.HomeClient
import com.watermelon.hitoindividualaccesodatos.home.data.network.dto.Deposit
import javax.inject.Inject

class MoneyTransaction @Inject constructor(private val homeClient: HomeClient) {

    suspend fun deposit(deposit: Deposit){
        homeClient.deposit(deposit)
    }

    suspend fun withdraw(deposit: Deposit){
        homeClient.withdraw(deposit)
    }
}