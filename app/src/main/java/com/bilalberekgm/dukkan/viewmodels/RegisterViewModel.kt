package com.bilalberekgm.dukkan.viewmodels

import android.net.Network
import android.util.Log
import android.widget.SimpleCursorAdapter.ViewBinder
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bilalberekgm.dukkan.models.RegisterResponse
import com.bilalberekgm.dukkan.repositories.RegisterRepository
import com.bilalberekgm.dukkan.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerRepository: RegisterRepository
):ViewModel() {

    private val _registerState = MutableStateFlow<RegisterResponse?>(null)
    val registerState: StateFlow<RegisterResponse?> = _registerState

    private val _isLoading:MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessageState = MutableStateFlow("")
    val errorMessageState: StateFlow<String> = _errorMessageState

    private val _isErrorOccurredSate  = MutableStateFlow(false)
    val isErrorOccurredState: StateFlow<Boolean> = _isErrorOccurredSate

    fun registerUser(
        firstName:String,
        lastName:String,
        email:String,
        password:String
    ){
       viewModelScope.launch(Dispatchers.IO){
        registerRepository.registerUser(
           firstName = firstName,
           lastName = lastName,
           email = email,
           password = password
       ).collect{ networkResponse ->
           when(networkResponse){
               is NetworkResult.Loading -> {
                   _isLoading.emit(true)
               }
               is NetworkResult.Success -> {
                   _registerState.emit(networkResponse.data)
                   _isErrorOccurredSate.emit(false)
               }
               is NetworkResult.Error -> {
                   Log.d("CONTROL NAVIGATION", "Navigation message: error")
                   _isErrorOccurredSate.emit(true)
                   _errorMessageState.emit(networkResponse.message!!)

               }
           }
       }
       }
    }

    fun resetStates() {
        _registerState.value = null
        _isLoading.value = false
        _isErrorOccurredSate.value = false
    }

    fun resetErrorState() {
        _errorMessageState.value = ""
    }
}