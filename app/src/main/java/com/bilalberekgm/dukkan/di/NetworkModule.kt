package com.bilalberekgm.dukkan.di

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.bilalberekgm.dukkan.datastore.DataStoreKeys
import com.bilalberekgm.dukkan.service.ApiService
import com.bilalberekgm.dukkan.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrlForEmulator

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrlForRealDevice

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule() {

    @Provides
    @Singleton
    fun provideRetrofit(@BaseUrlForRealDevice baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @BaseUrlForEmulator
    fun provideBaseUrlForEmulator(): String {
        return Constants.BASE_URL_EMULATOR_DEVICE
    }

    @Provides
    @BaseUrlForRealDevice
    fun provideBaseUrlForRealDevice(): String {
        return Constants.BASE_URL_REAL_DEVICE
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(tokenInterceptor: Interceptor):OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(tokenInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideTokenInterceptor(
        dataStore: DataStore<Preferences>
    ):Interceptor{
            return Interceptor{ chain ->
                val originalRequest = chain.request()
                val requestBuilder = originalRequest.newBuilder()

                val url = originalRequest.url

                val shouldAddToken =
                            !url.toString().contains("/api/v1/auth/signin")
                            &&
                            !url.toString().contains("/api/v1/auth/signup")
                if(shouldAddToken){
                    val token = runBlocking {
                        dataStore.data.first()[DataStoreKeys.JWT_TOKEN_KEY] ?: ""
                    }
                    requestBuilder.addHeader("Authorization", "Bearer $token")
                }

                val newRequest = requestBuilder.build()
                chain.proceed(newRequest)
            }
    }
}