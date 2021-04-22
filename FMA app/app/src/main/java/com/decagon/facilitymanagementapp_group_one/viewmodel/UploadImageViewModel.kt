package com.decagon.facilitymanagementapp_group_one.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decagon.facilitymanagementapp_group_one.data.FacilityMgtRepository
import com.decagon.facilitymanagementapp_group_one.data.ResponseBody
import com.decagon.facilitymanagementapp_group_one.data.Status
import com.decagon.facilitymanagementapp_group_one.model.User
import com.decagon.facilitymanagementapp_group_one.network.FacilityApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class UploadImageViewModel @Inject constructor(
    private val facilityRepo: FacilityMgtRepository
): ViewModel() {

    private var _responseBody = MutableLiveData<ResponseBody?>()
    val responseBody : LiveData<ResponseBody?>
        get() = _responseBody

    fun uploadImage(image: MultipartBody.Part, token : String){
        viewModelScope.launch {
            try {
                val responseBody = facilityRepo.uploadImage(image, token)
                _responseBody.value = responseBody
            } catch (e : Exception){
                _responseBody.value = null
                e.message?.let { Log.d("Response", it) }
            }
        }
    }

    private var _result = MutableLiveData<Status?>()
    val result : LiveData<Status?>
        get() = _result

//    fun uploadProfile(user : User, token: String) {
//        viewModelScope.launch {
//            try {
//                val response = facilityRepo.updateProfile(user, token)
//                _result.value = response
//                Log.d("Profile update: ", response.toString())
//            } catch (e : Exception) {
//                _result.value = null
//                e.message?.let { Log.d("Response", it) }
//            }
//        }
//    }
}