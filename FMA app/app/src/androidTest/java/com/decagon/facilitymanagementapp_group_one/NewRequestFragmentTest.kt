package com.decagon.facilitymanagementapp_group_one

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.RootMatchers.isPlatformPopup
import androidx.test.espresso.matcher.ViewMatchers.*
import com.decagon.facilitymanagementapp_group_one.ui.HomeFragment
import com.decagon.facilitymanagementapp_group_one.ui.NewRequestFragment
import junit.framework.TestCase
import org.hamcrest.CoreMatchers.*
import org.junit.Before
import org.junit.Test

class NewRequestFragmentTest : TestCase(){
    @Before
    override fun setUp(){
        val scenario =
            launchFragmentInContainer<NewRequestFragment>(themeResId = R.style.Theme_FacilityManagementAppGroupOne)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

//    @Test
//    fun test_custom_spinner_can_click_and_display_options(){
//        onView(withId(R.id.fragment_new_request_categories_at)).perform(click())
//    }

//    @Test
//    fun test_subject_inputText_is_displayed_and_accepts_text() {
//        onView(withId(R.id.fragment_newRequest_request_subject_et)).check(matches(isDisplayed()))
//            .perform(typeText("No light"))
//    }
//
//    @Test
//    fun test_description_inputText_is_displayed_and_accepts_text() {
//        onView(withId(R.id.fragment_new_request_description_et)).check(matches(isDisplayed()))
//            .perform(typeText("There has been no light in the facility."))
//    }
//
//    @Test
//    fun test_submit_button_is_displayed() {
//        onView(withId(R.id.fragment_new_request_submitBtn_btn)).check(matches(isDisplayed()))
//    }

//    @Test
//    fun test_back_button_is_displayed() {
//        onView(withId(R.id.fragment_new_request_backBtn_btn)).check(matches(isDisplayed()))
//    }
}