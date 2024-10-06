package com.bilalberekgm.dukkan.di

import com.bilalberekgm.dukkan.repositories.LoginRepository
import com.bilalberekgm.dukkan.repositories.LoginRepositoryImp
import com.bilalberekgm.dukkan.repositories.RegisterRepository
import com.bilalberekgm.dukkan.repositories.RegisterRepositoryImp
import com.bilalberekgm.dukkan.repositories.UserPreferencesRepository
import com.bilalberekgm.dukkan.repositories.UserPreferencesRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindingModule {

    @Binds
    abstract fun  bindRegisterRepositoryToItsImplementation(
        repository: RegisterRepositoryImp
    ): RegisterRepository

    @Binds
    abstract fun bindUserPreferencesRepository(
        repository: UserPreferencesRepositoryImp
    ):UserPreferencesRepository

    @Binds
    abstract fun bindUserLoginRepository(
        repository: LoginRepositoryImp
    ): LoginRepository
}