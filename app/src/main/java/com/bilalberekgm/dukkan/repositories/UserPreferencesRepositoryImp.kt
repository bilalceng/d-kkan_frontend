package com.bilalberekgm.dukkan.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.bilalberekgm.dukkan.datastore.DataStoreKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferencesRepositoryImp @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : UserPreferencesRepository {

    override val jwt: Flow<String?> = dataStore.data.map { preferences ->
        preferences[DataStoreKeys.JWT_TOKEN_KEY]
    }

    override val role: Flow<String?> = dataStore.data.map { preferences ->
        preferences[DataStoreKeys.USER_ROLE]
    }

    override val userFirstName: Flow<String?> = dataStore.data.map { preferences ->
        preferences[DataStoreKeys.USER_FIRST_NAME_KEY]
    }

    override val userLastName: Flow<String?> = dataStore.data.map { preferences ->
        preferences[DataStoreKeys.USER_LAST_NAME_KEY]
    }

    override val email: Flow<String?> = dataStore.data.map { preferences ->
        preferences[DataStoreKeys.EMAIL_KEY]
    }

    override val password: Flow<String?> = dataStore.data.map { preferences ->
        preferences[DataStoreKeys.PASSWORD]
    }

    override suspend fun saveUserFirstName(firstName: String) {
        dataStore.edit { preferences ->
            preferences[DataStoreKeys.USER_FIRST_NAME_KEY] = firstName
        }
    }

    override suspend fun saveUserLastName(lastName: String) {
        dataStore.edit { preferences ->
            preferences[DataStoreKeys.USER_LAST_NAME_KEY] = lastName
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
