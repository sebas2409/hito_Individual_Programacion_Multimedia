package com.watermelon.hitoindividualaccesodatos.login.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {

    private val _id = MutableStateFlow("")
    val id = _id.asStateFlow()

    fun handleId(id: String) {
        _id.value = id
    }
}