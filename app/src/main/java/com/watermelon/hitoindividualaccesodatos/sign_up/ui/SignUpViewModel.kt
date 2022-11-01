package com.watermelon.hitoindividualaccesodatos.sign_up.ui

import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.watermelon.hitoindividualaccesodatos.sign_up.domain.Account
import com.watermelon.hitoindividualaccesodatos.sign_up.services.AccountCreator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val accountCreator: AccountCreator) :
    ViewModel() {

    private val _show = MutableStateFlow(false)
    val show = _show.asStateFlow()

    private val _id = MutableStateFlow("")
    val id = _id.asStateFlow()

    private val _nombre = MutableStateFlow("")
    val nombre = _nombre.asStateFlow()

    private val _dni = MutableStateFlow("")
    val dni = _dni.asStateFlow()

    private val _saldo = MutableStateFlow("")
    val saldo = _saldo.asStateFlow()

    fun handleShow(value: Boolean) {
        _show.value = value
    }

    fun handleName(nombre: String) {
        _nombre.value = nombre
    }

    fun handleDni(dni: String) {
        _dni.value = dni
    }

    fun handleSaldo(saldo: String) {
        _saldo.value = saldo
    }

    fun copyToClipBoard(clipboardManager: ClipboardManager, id: String) {
        clipboardManager.setText(AnnotatedString(id))
        _show.value = false
    }

    fun createAccount(nombre: String, dni: String, saldo: String) {
        val balance = saldo.toDouble() + 100
        viewModelScope.launch {
            val rs = accountCreator.createAccount(Account(nombre, dni, balance))
            _id.value = rs.id
            _show.value = true
        }
    }
}