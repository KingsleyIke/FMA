package com.decagon.facilitymanagementapp_group_one.data

import androidx.lifecycle.LiveData
import com.decagon.facilitymanagementapp_group_one.model.*
import com.decagon.facilitymanagementapp_group_one.model.User
import com.decagon.facilitymanagementapp_group_one.network.FacilityApi
import com.decagon.facilitymanagementapp_group_one.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

class FacilityMgtRepository @Inject constructor(
    private val facilityApi: FacilityApi,
    private val feedDao: FeedDao
) {

    suspend fun uploadImage(image: MultipartBody.Part, token: String): ResponseBody {
        return facilityApi.changeProfilePicture(image, token)
    }

    suspend fun updateProfile(user: User, auth: String): Flow<DataState<Response<Void>>> = flow {
        emit(DataState.Waiting)
        try {
            val response = facilityApi.updateProfile(auth, user)
            emit(DataState.Success(response))
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }

    fun getDiskComplaint(): LiveData<List<Feed>>{
        return feedDao.getAllFeeds()
    }

    suspend fun pullComplaintAndSave(
        token: String,
        userId: String,
        pageNum: Int
    ) {
        withContext(Dispatchers.IO) {
            val response = withContext(Dispatchers.Default) {
                facilityApi.getUserComplaints(
                    token,
                    userId,
                    pageNum
                )
            }

            feedDao.insertFeed(*response.get())
        }
    }

    suspend fun deleteLocalComplaint(id: String){
        withContext(Dispatchers.IO){
            feedDao.deleteFeed(id)
        }
    }

    suspend fun deleteRemoteComplaint(token: String, complaintId: String): DeleteResponse {
        return withContext(Dispatchers.Default){
            facilityApi.deleteRequest(token, complaintId)
        }
    }

    suspend fun addNewComplaint(token: String, complaint: Complaint, feedId: String): ComplaintResponse{
        return withContext(Dispatchers.IO){
            facilityApi.addComplaint(token, feedId, complaint)
        }
    }

}