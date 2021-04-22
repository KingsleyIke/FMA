package com.decagon.facilitymanagementapp_group_one.di

import android.content.Context
import androidx.room.Room
import com.decagon.facilitymanagementapp_group_one.data.AppDatabase
import com.decagon.facilitymanagementapp_group_one.network.AuthApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    //Providing a single instance of the database and its associated DAOs
    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "fma-db"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideCommentDao(appDatabase: AppDatabase) = appDatabase.commentsDao()

    @Singleton
    @Provides
    fun provideFeedDao(appDatabase: AppDatabase) = appDatabase.feedsDao()

    @Singleton
    @Provides
    fun provideReplyDao(appDatabase: AppDatabase) = appDatabase.repliesDao()

}