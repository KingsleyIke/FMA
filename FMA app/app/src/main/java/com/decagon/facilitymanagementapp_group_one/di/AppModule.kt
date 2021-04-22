package com.decagon.facilitymanagementapp_group_one.di


import android.content.Context
import com.decagon.facilitymanagementapp_group_one.data.AppDatabase
import com.decagon.facilitymanagementapp_group_one.network.FacilityApi
import com.decagon.facilitymanagementapp_group_one.utils.EditValidation
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
//    @Singleton
//    @Provides
//    fun provideRetrofit (gson: Gson): Retrofit = Retrofit.Builder()
//                .baseUrl("http://fma-develop.herokuapp.com/api/v1/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//
//    @Provides
//    fun provideGson ():Gson = GsonBuilder().create()
//
//    @Provides
//    fun provideFacilityApi (retrofit: Retrofit): FacilityApi = retrofit.create()
//
//    //Providing a single instance of the database and its associated DAOs
//
//    @Singleton
//    @Provides
//    fun provideAppDatabase (@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)
//
//    @Singleton
//    @Provides
//    fun provideCommentDao(appDatabase: AppDatabase) = appDatabase.commentsDao()
//
//    @Singleton
//    @Provides
//    fun provideFeedDao(appDatabase: AppDatabase) = appDatabase.feedsDao()
//
//    @Singleton
//    @Provides
//    fun provideReplyDao(appDatabase: AppDatabase) = appDatabase.repliesDao()
//
//    @Singleton
//    @Provides
//    fun provideValidation(): EditValidation {
//        return EditValidation()
//    }
}