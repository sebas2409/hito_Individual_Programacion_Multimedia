package com.watermelon.hitoindividualaccesodatos.login.ui

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.watermelon.hitoindividualaccesodatos.login.service.LoginService
import com.watermelon.hitoindividualaccesodatos.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginService: LoginService) : ViewModel() {

    private val _id = MutableStateFlow("")
    val id = _id.asStateFlow()

    fun handleId(id: String) {
        _id.value = id
    }

    fun handleLogin(id: String, navHostController: NavHostController, context: Context) {
        viewModelScope.launch {
            val rs = loginService.login(id)
            if (rs.id != "") {
                navHostController.navigate("home/${rs.id}")
            } else {
                Toast.makeText(context, "El id es incorrecto!", Toast.LENGTH_LONG).show()
            }
        }
    }
}