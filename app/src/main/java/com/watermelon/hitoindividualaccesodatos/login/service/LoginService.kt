package com.watermelon.hitoindividualaccesodatos.login.service

import com.watermelon.hitoindividualaccesodatos.login.data.network.LoginClient
import com.watermelon.hitoindividualaccesodatos.login.data.network.response.LoginResponse
import javax.inject.Inject

class LoginService @Inject constructor(private val loginClient: LoginClient) {

    suspend fun login(id: String): LoginResponse {
        return loginClient.login(id).body() ?: LoginResponse("", "", 0.0)
    }
}