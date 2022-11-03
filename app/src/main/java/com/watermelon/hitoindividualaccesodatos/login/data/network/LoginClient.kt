package com.watermelon.hitoindividualaccesodatos.login.data.network

import com.watermelon.hitoindividualaccesodatos.login.data.network.response.LoginResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LoginClient {

    @GET("/login/{id}")
    suspend fun login(@Path(value = "id") id: String) :Response<LoginResponse>
}