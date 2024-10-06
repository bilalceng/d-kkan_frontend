package com.bilalberekgm.dukkan.repositories

import com.bilalberekgm.dukkan.models.RegisterResponse
import com.bilalberekgm.dukkan.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {
    suspend fun registerUser(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): Flow<NetworkResult<RegisterResponse>>
}