package com.watermelon.hitoindividualaccesodatos.home.data.network

import com.watermelon.hitoindividualaccesodatos.home.data.network.dto.Deposit
import com.watermelon.hitoindividualaccesodatos.home.data.network.response.Transaction
import com.watermelon.hitoindividualaccesodatos.sign_up.data.network.response.AccountResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface HomeClient {
    @GET("/account/{id}")
    suspend fun getAccount(@Path("id") id: String): Response<AccountResponse>

    @GET("/transaction/{id}")
    suspend fun getTransaction(@Path("id") id:String): Response<ArrayList<Transaction>>

    @POST("/deposit")
    suspend fun deposit(@Body deposit: Deposit)

    @POST("/withdraw")
    suspend fun withdraw(@Body deposit: Deposit)
}