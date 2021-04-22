package com.decagon.facilitymanagementapp_group_one.model

import com.decagon.facilitymanagementapp_group_one.utils.*

//data class User(
//    val firstName: String? = "",
//    val lastName: String? = "",
//    val displayName: String? = "",
//    val mail: String? = "",
//    val userName: String? = "",
//    val gender: String? = "",
//    val squad: String?= "",
//    val stack: String? = "",
//    val phoneNumber: String? = "",
//    val imageUrl: String? = ""
//    )

//fun Map<String, String?>.mapToUser(): User {
//    return User(
//        firstName = this[USER_FIRST_NAME],
//        lastName = this[USER_LAST_NAME],
//        displayName = this[USER_DISPLAY_NAME],
//        mail = this[USER_MAIL],
//        userName = this[USER_NAME],
//        gender = this[GENDER],
//        stack = this[STACK],
//        squad = this[SQUAD],
//        phoneNumber = this[MOBILE],
//        imageUrl = this[IMAGE_URL]
//    )
//}

data class User(
    val firstName: String? = null,
    val lastName: String? = null,
    val userName: String? = null,
    val gender: String? = null,
    val squad: String?= null,
    val phoneNumber: String? = null,
)

fun Map<String, String?>.mapToUser(): User {
    return User(
        firstName = this[USER_FIRST_NAME],
        lastName = this[USER_LAST_NAME],
        userName = this[USER_NAME],
        gender = this[GENDER],
        squad = this[SQUAD],
        phoneNumber = this[MOBILE],
    )
}
