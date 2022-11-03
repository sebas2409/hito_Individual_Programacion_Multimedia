package com.watermelon.hitoindividualaccesodatos.home.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.watermelon.hitoindividualaccesodatos.home.data.network.response.Transaction
import com.watermelon.hitoindividualaccesodatos.home.service.GetAccountInfo
import com.watermelon.hitoindividualaccesodatos.home.service.GetTransactionList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAccountInfo: GetAccountInfo,
    private val getTransactionList: GetTransactionList
) : ViewModel() {

    private val _nombre = MutableStateFlow("")
    val nombre = _nombre.asStateFlow()

    private val _cc = MutableStateFlow("")
    val cc = _cc.asStateFlow()

    private val _balance = MutableStateFlow(0.0)
    val balance = _balance.asStateFlow()

    private val _transacctionList = MutableStateFlow(arrayListOf<Transaction>())
    val transactionList = _transacctionList.asStateFlow()

    fun getInfo(id: String) {
        viewModelScope.launch {
            val rs = getAccountInfo.getInfo(id)
            _nombre.value = rs.nombre
            _cc.value = rs.cc
            _balance.value = rs.balance
        }
    }

    fun getTransacction(id: String) {
        viewModelScope.launch {
            val rs = getTransactionList.getTransactionInfo(id)
            rs.sortByDescending { t -> t.timeStamp }
            _transacctionList.value = rs
        }
    }
}