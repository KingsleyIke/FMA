package com.decagon.facilitymanagementapp_group_one.utils

/**
 * @author Francis Akpan
 */
sealed class DataState<out R> {

    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val exception: Exception) : DataState<Nothing>()
    object Waiting : DataState<Nothing>()
}
