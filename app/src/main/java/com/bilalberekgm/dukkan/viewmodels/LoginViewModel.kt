package com.bilalberekgm.dukkan.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bilalberekgm.dukkan.models.LoginResponse
import com.bilalberekgm.dukkan.models.RegisterResponse
import com.bilalberekgm.dukkan.repositories.LoginRepository
import com.bilalberekgm.dukkan.service.ApiService
import com.bilalberekgm.dukkan.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
):ViewModel() {

    private val _loginState = MutableStateFlow<LoginResponse?>(null)
    val registerState: StateFlow<LoginResponse?> = _loginState

    private val _isErrorOccurredSate  = MutableStateFlow(false)
    val isErrorOccurredState: StateFlow<Boolean> = _isErrorOccurredSate

    private val _isLoading:MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    var email = loginRepository.email
    var password = loginRepository.password
    var jwt = loginRepository.jwt
    var role = loginRepository.role

    fun login(email:String , password:String){
        viewModelScope.launch(Dispatchers.IO) {
          loginRepository.login(
                email,
                password
            ).collect{ result ->
                when(result) {

                    is NetworkResult.Loading -> {
                        _isLoading.emit(true)
                    }
                    is NetworkResult.Success -> {
                        _loginState.emit(result.data)
                        _isErrorOccurredSate.emit(false)
                    }
                    is NetworkResult.Error -> {
                        _isErrorOccurredSate.emit(true)
                    }
                }
           }
        }
    }
    fun saveEmail(email:String){
        viewModelScope.launch {
            loginRepository.saveEmail(email)
        }
    }
    fun saveUserPassword(password:String){
        viewModelScope.launch {
            loginRepository.saveUserPassword(password)
        }
    }
    fun saveUserJwtToken(jwt:String){
        viewModelScope.launch {
            loginRepository.saveUserJwtToken(jwt)
        }
    }
    fun saveUserRole(role:String){
        viewModelScope.launch {
            loginRepository.saveUserRole(role)
        }
    }

}