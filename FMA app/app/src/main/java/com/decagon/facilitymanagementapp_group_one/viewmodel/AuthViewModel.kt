package com.decagon.facilitymanagementapp_group_one.viewmodel

import androidx.lifecycle.*
import com.decagon.facilitymanagementapp_group_one.data.AuthRepository
import com.decagon.facilitymanagementapp_group_one.model.AuthResponse
import com.decagon.facilitymanagementapp_group_one.model.RequestBody
import com.decagon.facilitymanagementapp_group_one.model.UserProfile
import com.decagon.facilitymanagementapp_group_one.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Francis Akpan
 */
@HiltViewModel
class  AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _dataState = MutableLiveData<DataState<AuthResponse>>()
    val dataState: MutableLiveData<DataState<AuthResponse>>
        get() = _dataState

    private var _result = MutableLiveData<DataState<UserProfile>>()
    val result : LiveData<DataState<UserProfile>>
        get() = _result

    fun attemptAuth(token: String) {
        viewModelScope.launch {
            authRepository.authenticate(token)
                .onEach { dataState ->
                    _dataState.value = dataState
                }.launchIn(viewModelScope)
        }
    }

    fun getUserProfile(token: String, id: String) {
        viewModelScope.launch {
            authRepository.getUser(token, id)
                .onEach { result ->
                    _result.value = result
                }.launchIn(viewModelScope)
        }
    }
}