package com.bilalberekgm.dukkan.repositories

import android.util.Log
import com.bilalberekgm.dukkan.models.ErrorResponse
import com.bilalberekgm.dukkan.models.RegisterRequest
import com.bilalberekgm.dukkan.models.RegisterResponse
import com.bilalberekgm.dukkan.service.ApiService
import com.bilalberekgm.dukkan.utils.NetworkResult
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterRepositoryImp @Inject constructor(private val apiService: ApiService): RegisterRepository{
    override suspend fun registerUser(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): Flow<NetworkResult<RegisterResponse>> = flow {

        try {
            val response = apiService.register(
                registerRequest = RegisterRequest(firstName, lastName, email, password)
            )
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    emit(NetworkResult.Success(body))
                } else {
                    emit(NetworkResult.Error("Empty response body"))
                }
            } else if (response.code() == 409) {
                Log.d("TAG", "registerUser: 409 ")

                // Read the error body as a string
                val errorBody = response.errorBody()?.string()
                Log.d("TAG", " here is the error body: $errorBody")

                // Parse the error body into ErrorResponse
                val errorResponse = Gson().fromJson(errorBody, ErrorResponse::class.java)
                Log.d("TAG", " here is the error body2: $errorResponse")

                emit(NetworkResult.Error(
                    errorResponse?.message ?: "An unknown error occurred"
                ))
            }
        } catch (e: Exception) {
            emit(NetworkResult.Error("An error occurred during registration"))
        }
    }

}