package com.decagon.facilitymanagementapp_group_one

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.decagon.facilitymanagementapp_group_one.ui.FailedAuthorizationFragment
import org.junit.Before

import org.junit.Test

class FailedAuthorizationFragmentTest {

    @Before
    fun setUp() {
        val scenario = launchFragmentInContainer<FailedAuthorizationFragment>(
            themeResId = R.style.Theme_FacilityManagementAppGroupOne
        )

        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun isFragmentVisible() {
        onView(withId(R.id.failed_fragment)).check(matches(isDisplayed()))
    }
    @Test
    fun isToolbarVisible() {
        onView(withId(R.id.fragment_failed_authorization_tb)).check(matches(isDisplayed()))
    }

    @Test
    fun isErrorImageVisible() {
        onView(withId(R.id.fragment_failed_authorization_iv)).check(matches(isDisplayed()))
    }

    @Test
    fun isErrorMessageVisible() {
        onView(withId(R.id.fragment_failed_authorization_errorMessage_tv)).check(matches(isDisplayed()))
    }

    @Test
    fun isTryAgainButtonVisible() {
        onView(withId(R.id.fragment_failed_authorization_tryAgain_btn)).check(matches(isDisplayed()))
    }

    @Test
    fun isBackButtonVisible() {
        onView(withId(R.id.fragment_failed_authorization_back_btn)).check(matches(isDisplayed()))
    }

}