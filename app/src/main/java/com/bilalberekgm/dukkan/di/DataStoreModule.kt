package com.bilalberekgm.dukkan.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.bilalberekgm.dukkan.datastore.DataStoreKeys
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

private val Context.datastore by preferencesDataStore("user_preferences")

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDatastore(
        @ApplicationContext context: Context
    ): DataStore<Preferences>{
        return context.datastore
    }
}