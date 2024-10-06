package com.bilalberekgm.dukkan.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bilalberekgm.dukkan.repositories.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
): ViewModel() {

    var firstname = userPreferencesRepository.userFirstName
    var lastName = userPreferencesRepository.userLastName
    var email = userPreferencesRepository.email
    var password = userPreferencesRepository.password
    var jwt = userPreferencesRepository.jwt
    var role = userPreferencesRepository.role

     fun saveUserFirstName(firstName:String){
        viewModelScope.launch {
            userPreferencesRepository.saveUserFirstName(firstName)
        }
    }
    fun saveUserLastName(lastName: String){
        viewModelScope.launch {
            userPreferencesRepository.saveUserLastName(lastName)
        }
    }
    fun saveEmail(email:String){
        viewModelScope.launch {
            userPreferencesRepository.saveEmail(email)
        }
    }
    fun saveUserPassword(password:String){
        viewModelScope.launch {
            userPreferencesRepository.saveUserPassword(password)
        }
    }
    fun saveUserJwtToken(jwt:String){
        viewModelScope.launch {
            userPreferencesRepository.saveUserJwtToken(jwt)
        }
    }
    fun saveUserRole(role:String){
        viewModelScope.launch {
            userPreferencesRepository.saveUserRole(role)
        }
    }
}