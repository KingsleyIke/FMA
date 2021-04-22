package com.decagon.facilitymanagementapp_group_one.data

import com.decagon.facilitymanagementapp_group_one.model.AuthResponse
import com.decagon.facilitymanagementapp_group_one.model.RequestBody
import com.decagon.facilitymanagementapp_group_one.model.UserProfile
import com.decagon.facilitymanagementapp_group_one.network.AuthApi
import com.decagon.facilitymanagementapp_group_one.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

/**
 * @author Francis Akpan
 */
class AuthRepository constructor(
    private val api: AuthApi
) {
    suspend fun authenticate (
        token: String
    ): Flow<DataState<AuthResponse>> = flow {
        emit(DataState.Waiting)
        try {
            val response = api.authenticate(token)
            emit(DataState.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }

    suspend fun getUser (
        token: String,
        id: String
    ): Flow<DataState<UserProfile>> = flow {
        emit(DataState.Waiting)
        try {
            val response = api.getUser(token, id)
            emit(DataState.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }
}