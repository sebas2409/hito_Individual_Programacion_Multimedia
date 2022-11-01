package com.watermelon.hitoindividualaccesodatos.sign_up.services

import com.watermelon.hitoindividualaccesodatos.sign_up.data.network.SignUpClient
import com.watermelon.hitoindividualaccesodatos.sign_up.data.network.response.AccountResponse
import com.watermelon.hitoindividualaccesodatos.sign_up.domain.Account
import javax.inject.Inject

class AccountCreator @Inject constructor(private val signUpClient: SignUpClient) {

    suspend fun createAccount(account: Account): AccountResponse {
        return signUpClient.createAccount(account).body() ?: AccountResponse(
            "",
            "",
            "",
            "",
            0.0,
            ""
        )
    }
}