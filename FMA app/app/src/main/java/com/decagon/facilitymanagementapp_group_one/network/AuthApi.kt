package com.decagon.facilitymanagementapp_group_one.network

import com.decagon.facilitymanagementapp_group_one.model.AuthResponse
import com.decagon.facilitymanagementapp_group_one.model.RequestBody
import com.decagon.facilitymanagementapp_group_one.model.UserProfile
import retrofit2.http.*

/**
 * @author Francis Akpan
 */
interface AuthApi {
//    @Headers("Content-Type: application/json")
//    @POST("/api/v1/Auth/external-login")
//    suspend fun authenticate(@Body body: RequestBody): AuthResponse

    @POST("/api/v1/Auth/external-login")
    suspend fun authenticate(@Header("bearer") token: String): AuthResponse

    @GET("/api/v1/User/get-user/{userId}")
    suspend fun getUser(
        @Header("Authorization") token: String,
        @Path("userId") id: String
    ): UserProfile

}