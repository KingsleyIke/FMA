package com.decagon.facilitymanagementapp_group_one.utils

import javax.inject.Inject

class EditValidation  @Inject constructor() {

    /**
     * Validation function for mobile number validation
     */
    fun mobileValidate(text: String): Boolean {
        return if (text.take(3) == "080" && text.length == 11||
            text.take(3) == "070" && text.length == 11||
            text.take(3) == "090" && text.length == 11||
            text.take(3) == "081" && text.length == 11 ) {
            true
        } else text.take(5) == "23480" && text.length == 13||
                text.take(5) == "23481" && text.length == 13||
                text.take(5) == "23470" && text.length == 13||
                text.take(5) == "23490" && text.length == 13||
                text.take(4) == "+234" && text.length == 14
    }

    /**
     * Validation function for Squad Validation
     */
    fun squadValidation(text: String): Boolean {
        return text.take(2) == "SQ" ||  text.take(2) == "sq" && text.length == 5
    }

    fun stackValidation(text: String): Boolean {
        val stack = arrayOf("Android", "Node", ".Net", "QA", "Java", "IOS", "Python")
        return stack.contains(text)
    }
}