package com.watermelon.hitoindividualaccesodatos.sign_up.data.network

import com.watermelon.hitoindividualaccesodatos.sign_up.data.network.response.AccountResponse
import com.watermelon.hitoindividualaccesodatos.sign_up.domain.Account
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpClient {

    @POST("/create")
    suspend fun createAccount(@Body cuenta: Account): Response<AccountResponse>
}