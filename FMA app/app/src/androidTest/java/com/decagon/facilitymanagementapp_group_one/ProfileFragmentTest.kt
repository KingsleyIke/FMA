package com.decagon.facilitymanagementapp_group_one

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.decagon.facilitymanagementapp_group_one.ui.ProfileFragment
import org.junit.Before
import org.junit.Test

class ProfileFragmentTest{
    @Before
    fun setUp() {
        launchFragmentInHiltContainer<ProfileFragment>()
    }

    @Test
    fun isFragmentVisible() {
        onView(withId(R.id.profile_fragment)).check(matches(isDisplayed()))
    }
    @Test
    fun isToolbarVisible() {
        onView(withId(R.id.fragment_profile_tb)).check(matches(isDisplayed()))
    }

    @Test
    fun isProfileImageVisible() {
        onView(withId(R.id.fragment_profile_image)).check(matches(isDisplayed()))
    }

    @Test
    fun nameTextViewsVisible() {
        onView(withId(R.id.fragment_profile_name_tv)).check(matches(isDisplayed()))

        onView(withId(R.id.fragment_profile_nameValue_tv)).check(matches(isDisplayed()))
    }

    @Test
    fun emailTextViewsVisible() {
        onView(withId(R.id.fragment_profile_email_tv)).check(matches(isDisplayed()))

        onView(withId(R.id.fragment_profile_emailValue_tv)).check(matches(isDisplayed()))
    }

    @Test
    fun squadTextViewsVisible() {
        onView(withId(R.id.fragment_profile_squad_tv)).check(matches(isDisplayed()))

        onView(withId(R.id.fragment_profile_squadValue_tv)).check(matches(isDisplayed()))
    }

    @Test
    fun stackTextViewsVisible() {
        onView(withId(R.id.fragment_profile_stack_tv)).check(matches(isDisplayed()))

        onView(withId(R.id.fragment_profile_stackValue_tv)).check(matches(isDisplayed()))
    }

    @Test
    fun mobileTextViewsVisible() {
        onView(withId(R.id.fragment_profile_mobile_tv)).check(matches(isDisplayed()))

        onView(withId(R.id.fragment_profile_mobileValue_tv)).check(matches(isDisplayed()))
    }

    @Test
    fun editTextViewAndIconVisible() {
        onView(withId(R.id.fragment_profile_edit_tv)).check(matches(isDisplayed()))
    }

}
