package com.bilalberekgm.dukkan.repositories

import com.bilalberekgm.dukkan.models.LoginResponse
import com.bilalberekgm.dukkan.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    val password: Flow<String?>
    val email: Flow<String?>
    val jwt: Flow<String?>
    val role: Flow<String?>

    suspend fun login(email: String, password:String): Flow<NetworkResult<LoginResponse>>
    suspend fun saveEmail(email:String)
    suspend fun saveUserPassword(password:String)
    suspend fun saveUserJwtToken(jwt:String)
    suspend fun saveUserRole(role:String)


}