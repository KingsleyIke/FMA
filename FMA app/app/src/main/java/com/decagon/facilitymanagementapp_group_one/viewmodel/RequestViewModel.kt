package com.decagon.facilitymanagementapp_group_one.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.decagon.facilitymanagementapp_group_one.data.FacilityMgtRepository
import com.decagon.facilitymanagementapp_group_one.model.Complaint
import com.decagon.facilitymanagementapp_group_one.model.ComplaintResponse
import com.decagon.facilitymanagementapp_group_one.model.DataSource.feedsList
import com.decagon.facilitymanagementapp_group_one.model.Feed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RequestViewModel @Inject constructor(
    private val repository: FacilityMgtRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _deleteResult = MutableLiveData<Boolean>()
    val deleteResult: MutableLiveData<Boolean>
        get() = _deleteResult

    private var _addResult = MutableLiveData<ComplaintResponse>()
    val addResult: LiveData<ComplaintResponse>
        get() = _addResult

    val feeds = repository.getDiskComplaint()

    fun addRequest(request: Feed) {
        feedsList.add(request)
        Log.d("Request", request.type)
    }

    fun loadRequests(token: String, userId: String, pageNum: Int = 1) {
        viewModelScope.launch {
            try {
                Log.d("id", "id: $userId, token: $token")
                repository.pullComplaintAndSave("Bearer $token", userId, pageNum)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteRequest(token: String?, complaintId: String) {
        viewModelScope.launch {
            try {
                val response = repository.deleteRemoteComplaint("Bearer $token", complaintId)
                if (response.success) {
                    repository.deleteLocalComplaint(complaintId)
                }
                _deleteResult.value = response.success
            } catch (e: Exception) {
                e.printStackTrace()
                _deleteResult.value = false
            }
        }
    }

    fun addNewComplaint(token: String, complaint: Complaint, feedId: String) {
        viewModelScope.launch {
            try {
                val response = repository.addNewComplaint("Bearer $token", complaint, feedId)
                Log.d("ADD_RESPONSE","$response")
                _addResult.value = response
            } catch (e : Exception){
                e.message?.let { Log.d("COMPLAINT", it) }
            }
        }
    }
}