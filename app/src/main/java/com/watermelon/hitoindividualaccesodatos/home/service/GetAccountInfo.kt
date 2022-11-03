package com.watermelon.hitoindividualaccesodatos.home.service

import com.watermelon.hitoindividualaccesodatos.home.data.network.HomeClient
import com.watermelon.hitoindividualaccesodatos.sign_up.data.network.response.AccountResponse
import javax.inject.Inject

class GetAccountInfo @Inject constructor(private val homeClient: HomeClient){

    suspend fun getInfo(id:String):AccountResponse{
        return homeClient.getAccount(id).body()!!
    }
}