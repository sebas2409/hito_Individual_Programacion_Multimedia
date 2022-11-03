package com.watermelon.hitoindividualaccesodatos.home.data.network

import com.watermelon.hitoindividualaccesodatos.home.data.network.response.Transaction
import com.watermelon.hitoindividualaccesodatos.sign_up.data.network.response.AccountResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeClient {
    @GET("/account/{id}")
    suspend fun getAccount(@Path("id") id: String): Response<AccountResponse>

    @GET("/transaction/{id}")
    suspend fun getTransaction(@Path("id") id:String): Response<ArrayList<Transaction>>
}