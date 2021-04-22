package com.decagon.facilitymanagementapp_group_one.utils

import org.junit.Test

import org.junit.Assert.*

class EditValidationTest {

    val test = EditValidation()
    @Test
    fun mobileValidate() {
        val text = "08135425888"
        val actual =  test.mobileValidate(text)
        assertTrue(actual)
    }

        @Test
        fun mobileValidate_GivenNumberThatBeginsWith23481_ValidNumber() {
            val text = "2348135425888"
            val actual = test.mobileValidate(text)
            assertTrue(actual)
        }

        @Test
        fun mobileValidate_GivenNumberThatBeginsWith090_ValidNumber() {
            val text = "09035425888"
            val actual = test.mobileValidate(text)
            assertTrue(actual)

        }

        @Test
        fun mobileValidate_GivenANumber_InvalidNumber() {
            val text = "06035425888"
            val text2 = "2348435425888"
            val actual = test.mobileValidate(text)
            val actual2 = test.mobileValidate(text2)
            assertFalse(actual)
            assertFalse(actual2)
        }

    @Test
    fun squadValidation_GivenSquad_Valid() {
        val text = "SQ007"
        val actual = test.squadValidation(text)
        assertTrue(actual)
    }

    @Test
    fun squadValidation_GivenSquad_Invalid() {
        val text = "Q007"
        val actual = test.squadValidation(text)
        assertFalse(actual)
    }
}