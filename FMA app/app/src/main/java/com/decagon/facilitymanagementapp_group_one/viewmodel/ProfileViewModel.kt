package com.decagon.facilitymanagementapp_group_one.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decagon.facilitymanagementapp_group_one.data.FacilityMgtRepository
import com.decagon.facilitymanagementapp_group_one.data.Status
import com.decagon.facilitymanagementapp_group_one.model.User
import com.decagon.facilitymanagementapp_group_one.model.UserProfile
import com.decagon.facilitymanagementapp_group_one.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val facilityMgtRepository: FacilityMgtRepository) : ViewModel() {

    private var _response = MutableLiveData<DataState<Response<Void>>>()
    val response : LiveData<DataState<Response<Void>>>
        get() = _response

    fun updateUserProfile(user: User, token: String) {
        viewModelScope.launch {
            facilityMgtRepository.updateProfile(user, token)
                .onEach {
                    response ->
                    _response.value = response
                }.launchIn(viewModelScope)
        }
    }

}

