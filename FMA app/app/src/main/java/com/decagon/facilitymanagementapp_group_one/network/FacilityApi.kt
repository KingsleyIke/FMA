package com.decagon.facilitymanagementapp_group_one.network

import androidx.lifecycle.LiveData
import com.decagon.facilitymanagementapp_group_one.data.ResponseBody
import com.decagon.facilitymanagementapp_group_one.model.*
import retrofit2.http.*
import okhttp3.MultipartBody
import retrofit2.Response

interface FacilityApi {

    @GET("comments")
    suspend fun getComment(): List<Comment>

    @GET("posts")
    suspend fun getRequest(): List<Feed>

    @POST("comments")
    suspend fun postComment(): Comment

    @POST("comments")
    suspend fun postRequest(): Feed

    @PATCH("/api/v1/User/update-profile")
    suspend fun updateProfile(
        @Header("Authorization") token: String,
        @Body user: User
    ) : Response<Void>

    @Multipart
    @PATCH("api/v1/User/change-picture")
    suspend fun changeProfilePicture(
        @Part image: MultipartBody.Part,
        @Header("Authorization") token: String
    ): ResponseBody

    @GET("/api/v1/Feed/get-user-complaints/{userId}/{pageNumber}")
    suspend fun getUserComplaints(
        @Header("Authorization") token: String,
        @Path("userId") id: String,
        @Path("pageNumber") pageNum: Int
    ): ComplaintResponse

    @DELETE("/api/v1/Feed/delete-complaint/{complaintId}")
    suspend fun deleteRequest(
        @Header("Authorization") token: String,
        @Path("complaintId") complaintId: String
    ): DeleteResponse

    @POST("/api/v1/Feed/{feedId}/add-complaint")
    suspend fun addComplaint(
        @Header("Authorization") token: String,
        @Path("feedId") feedId : String,
        @Body complaint: Complaint
    ): ComplaintResponse
}