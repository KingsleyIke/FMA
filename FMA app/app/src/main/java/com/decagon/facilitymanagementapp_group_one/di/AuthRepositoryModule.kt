package com.decagon.facilitymanagementapp_group_one.di

import com.decagon.facilitymanagementapp_group_one.data.AuthRepository
import com.decagon.facilitymanagementapp_group_one.network.AuthApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author Francis Akpan
 */
@InstallIn(SingletonComponent::class)
@Module
object AuthRepositoryModule {

    @Singleton
    @Provides
    fun provideAuthRepository(
        api: AuthApi
    ): AuthRepository {
        return AuthRepository(api)
    }
}