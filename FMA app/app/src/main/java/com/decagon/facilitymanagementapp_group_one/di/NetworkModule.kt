package com.decagon.facilitymanagementapp_group_one.di

import com.decagon.facilitymanagementapp_group_one.network.AuthApi
import com.decagon.facilitymanagementapp_group_one.network.FacilityApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

/**
 * Classes or interfaces that can't be constructor injected are
 * provided here.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideGson ():Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit = Retrofit.Builder()
        .baseUrl("http://fma-develop.herokuapp.com/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    fun provideAuthApi( retrofit: Retrofit): AuthApi = retrofit.create()

    @Provides
    fun provideFacilityApi (retrofit: Retrofit): FacilityApi = retrofit.create()
}