package com.watermelon.hitoindividualaccesodatos.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.watermelon.hitoindividualaccesodatos.home.data.network.dto.Deposit
import com.watermelon.hitoindividualaccesodatos.home.data.network.response.Transaction
import com.watermelon.hitoindividualaccesodatos.home.service.GetAccountInfo
import com.watermelon.hitoindividualaccesodatos.home.service.GetTransactionList
import com.watermelon.hitoindividualaccesodatos.home.service.MoneyTransaction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAccountInfo: GetAccountInfo,
    private val getTransactionList: GetTransactionList,
    private val moneyTransaction: MoneyTransaction
) : ViewModel() {

    private val _nombre = MutableStateFlow("")
    val nombre = _nombre.asStateFlow()

    private val _cc = MutableStateFlow("")
    val cc = _cc.asStateFlow()

    private val _balance = MutableStateFlow(0.0)
    val balance = _balance.asStateFlow()

    private val _transacctionList = MutableStateFlow(arrayListOf<Transaction>())
    val transactionList = _transacctionList.asStateFlow()

    private val _showDepositDialog = MutableStateFlow(false)
    val showDepositDialog = _showDepositDialog.asStateFlow()

    private val _showWithdrawDialog = MutableStateFlow(false)
    val showWithdrawDialog = _showWithdrawDialog.asStateFlow()

    private val _cantidad = MutableStateFlow("")
    val cantidad = _cantidad.asStateFlow()

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

    fun handleCantidad(cantidad: String) {
        _cantidad.value = cantidad
    }

    fun handleDepositDialog(show: Boolean) {
        _showDepositDialog.value = show
    }

    fun handleWithdrawDialog(show: Boolean) {
        _showWithdrawDialog.value = show
    }

    fun deposit(deposit: Deposit) {
        viewModelScope.launch {
            moneyTransaction.deposit(deposit)
            getTransacction(deposit.clientId)
            getInfo(deposit.clientId)
            _showDepositDialog.value = false
            _cantidad.value = ""
        }
    }

    fun withdraw(deposit: Deposit) {
        viewModelScope.launch {
            moneyTransaction.withdraw(deposit)
            getTransacction(deposit.clientId)
            getInfo(deposit.clientId)
            _showWithdrawDialog.value = false
            _cantidad.value = ""
        }
    }
}