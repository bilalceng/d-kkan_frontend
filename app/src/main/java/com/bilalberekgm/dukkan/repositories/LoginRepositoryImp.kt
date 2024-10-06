package com.bilalberekgm.dukkan.repositories

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.bilalberekgm.dukkan.datastore.DataStoreKeys
import com.bilalberekgm.dukkan.models.ErrorResponse
import com.bilalberekgm.dukkan.models.LoginRequest
import com.bilalberekgm.dukkan.models.LoginResponse
import com.bilalberekgm.dukkan.service.ApiService
import com.bilalberekgm.dukkan.utils.NetworkResult
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoginRepositoryImp @Inject constructor(
    private val apiService: ApiService,
    private val dataStore: DataStore<Preferences>
): LoginRepository {
    override val email: Flow<String?> = dataStore.data.map { preferences ->
        preferences[DataStoreKeys.EMAIL_KEY]
    }

    override val password: Flow<String?> = dataStore.data.map { preferences ->
        preferences[DataStoreKeys.PASSWORD]
    }


    override val jwt: Flow<String?> = dataStore.data.map { preferences ->
        preferences[DataStoreKeys.JWT_TOKEN_KEY]
    }

    override val role: Flow<String?> = dataStore.data.map { preferences ->
        preferences[DataStoreKeys.USER_ROLE]
    }

    override suspend fun login(email: String, password: String):Flow<NetworkResult<LoginResponse>> = flow {
     val response =  apiService.login(
          LoginRequest(
              email = email,
              password = password
          )
         )
        if(response.isSuccessful){
            val body = response.body()
            if(body != null){
                emit(NetworkResult.Success(body))
            }else{
                emit(NetworkResult.Error("Response Body is Empty"))
            }

        } else if (response.code() == 403) {
            Log.d("TAG", "registerUser: 403")
            emit(NetworkResult.Error(
                "User not found or unauthorized"
            ))
        }
    }

    override suspend fun saveEmail(email: String) {
        dataStore.edit { preferences ->
            preferences[DataStoreKeys.EMAIL_KEY] = email
        }
    }

    override suspend fun saveUserPassword(password: String) {
        dataStore.edit { preferences ->
            preferences[DataStoreKeys.PASSWORD] = password
        }
    }

    override suspend fun saveUserJwtToken(jwt: String) {
        dataStore.edit { preferences ->
            preferences[DataStoreKeys.JWT_TOKEN_KEY] = jwt
        }
    }

    override suspend fun saveUserRole(role: String) {
        dataStore.edit { preferences ->
            preferences[DataStoreKeys.USER_ROLE] = role
        }
    }

}