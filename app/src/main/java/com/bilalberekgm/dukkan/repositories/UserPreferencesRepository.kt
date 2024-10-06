package com.bilalberekgm.dukkan.repositories

import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    val userFirstName: Flow<String?>
    val userLastName:Flow<String?>
    val email:Flow<String?>
    val password:Flow<String?>
    val jwt:Flow<String?>
    val role:Flow<String?>

    suspend fun saveUserFirstName(firstName:String)
    suspend fun saveUserLastName(lastName: String)
    suspend fun saveEmail(email:String)
    suspend fun saveUserPassword(password:String)
    suspend fun saveUserJwtToken(jwt:String)
    suspend fun saveUserRole(role:String)

}