package com.watermelon.hitoindividualaccesodatos.home.service

import com.watermelon.hitoindividualaccesodatos.home.data.network.HomeClient
import com.watermelon.hitoindividualaccesodatos.home.data.network.response.Transaction
import javax.inject.Inject

class GetTransactionList @Inject constructor(private val homeClient: HomeClient) {

    suspend fun getTransactionInfo(id: String): ArrayList<Transaction> {
        return homeClient.getTransaction(id).body()!!
    }

}