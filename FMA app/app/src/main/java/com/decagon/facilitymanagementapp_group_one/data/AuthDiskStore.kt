package com.decagon.facilitymanagementapp_group_one.data

import android.app.Activity
import android.content.Context
import com.decagon.facilitymanagementapp_group_one.model.User
import com.decagon.facilitymanagementapp_group_one.model.UserProfile
import com.decagon.facilitymanagementapp_group_one.utils.*
import javax.inject.Inject

/**
 * @author Francis Akpan
 * Manages Authorization token from the server.
 */
class AuthDiskStore @Inject constructor(
    private val activity: Activity
) {

    /**
     * Save auth token from server into device disk.
     */
    fun saveToken(id: String, token: String, isProfileCompleted: Boolean) {
        val sharedPref = activity.getSharedPreferences(AUTH_PREF, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString(ID, id)
            putString(TOKEN, token)
            putBoolean(IS_PROFILE_COMPLETED,isProfileCompleted )
            apply()
        }
    }

    /**
     *
     */
    fun saveUser(
        firstName: String? = null,
        lastName: String? = null,
        name: String? = null,
        userName: String? = null,
        gender: String? = null,
        mail: String? = null,
        stack: String? = null,
        squad: String? = null,
        mobile: String? = null,
        imageUrl: String? = null
    ) {
        val sharedPref = activity.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString(USER_FIRST_NAME, firstName)
            putString(USER_LAST_NAME, lastName)
            putString(USER_DISPLAY_NAME, name)
            putString(USER_NAME, userName)
            putString(USER_MAIL, mail)
            putString(GENDER, gender)
            putString(IMAGE_URL, imageUrl)
            putString(STACK, stack)
            putString(SQUAD, squad)
            putString(MOBILE, mobile)
            apply()
        }
    }

        fun saveUserProfile(
        user: UserProfile
    ) {
        val sharedPref = activity.getSharedPreferences(PROFILE_PREF, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString(USER_DISPLAY_NAME, user.data.firstName + " " + user.data.lastName)
            putString(USER_FIRST_NAME, user.data.firstName)
            putString(USER_LAST_NAME, user.data.lastName)
            putString(IMAGE_URL, user.data.avatarUrl)
            putString(USER_NAME, user.data.userName)
            putString(USER_MAIL, user.data.userName)
            putString(STACK, user.data.gender)
            putString(SQUAD, user.data.squad)
            putString(MOBILE, user.data.phoneNumber)
            putBoolean(IS_PROFILE_COMPLETED, user.data.isProfileCompleted)
            apply()
        }
    }

    fun getUserProfile(): Map<String, String?> {
        val sharedPref = activity.getSharedPreferences(PROFILE_PREF, Context.MODE_PRIVATE)
        return  mapOf(
            USER_DISPLAY_NAME to sharedPref.getString(USER_DISPLAY_NAME, ""),
            USER_FIRST_NAME to sharedPref.getString(USER_FIRST_NAME, ""),
            USER_LAST_NAME to sharedPref.getString(USER_LAST_NAME, ""),
            USER_NAME to sharedPref.getString(USER_NAME, ""),
            USER_MAIL to sharedPref.getString(USER_MAIL, ""),
            SQUAD to sharedPref.getString(SQUAD, ""),
            MOBILE to sharedPref.getString(MOBILE, ""),
            STACK to sharedPref.getString(STACK, ""),

        )
    }

    /**
     *
     */
    fun getUser(): Map<String, String?> {
        val sharedPref = activity.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE)
        return mapOf(
            USER_DISPLAY_NAME to sharedPref.getString(USER_DISPLAY_NAME, ""),
            USER_FIRST_NAME to sharedPref.getString(USER_FIRST_NAME, ""),
            USER_LAST_NAME to sharedPref.getString(USER_LAST_NAME, ""),
            USER_NAME to sharedPref.getString(USER_NAME, ""),
            GENDER to sharedPref.getString(GENDER, ""),
            USER_MAIL to sharedPref.getString(USER_MAIL, ""),
            STACK to sharedPref.getString(STACK, ""),
            SQUAD to sharedPref.getString(SQUAD, ""),
            MOBILE to sharedPref.getString(MOBILE, ""),
            IMAGE_URL to sharedPref.getString(IMAGE_URL, "")
        )
    }

    /**
     * Get token stored on device disk
     */
    fun getToken(): Map<String, Any?> {
        val sharedPref = activity.getSharedPreferences(AUTH_PREF, Context.MODE_PRIVATE)
        return mapOf(
            ID to sharedPref.getString(ID, ""),
            TOKEN to sharedPref.getString(TOKEN, ""),
            IS_PROFILE_COMPLETED to sharedPref.getBoolean(IS_PROFILE_COMPLETED, false)
        )
    }

    /**
     * Reset token, /this should be called when user is about to log out of the application.
     */
    fun reset(ref: String) {
        val sharedPref = activity.getSharedPreferences(ref, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            clear()
            apply()
        }
    }
}