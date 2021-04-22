package com.decagon.facilitymanagementapp_group_one

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.decagon.facilitymanagementapp_group_one.ui.EditProfileFragment
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Test

@HiltAndroidTest
class EditProfileFragmentTest {

    @Before
    fun setUp() {
        launchFragmentInHiltContainer<EditProfileFragment>(
//
        )
//        scenario.moveToState(Lifecycle.State.STARTED)
    }
    @Test
    fun isFragmentVisible() {
        Espresso.onView(withId(R.id.edit_profile_fragment))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    @Test
    fun isToolbarVisible() {
        Espresso.onView(withId(R.id.edit_fragment_profile_tb))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun isProfileImageVisible() {
        Espresso.onView(withId(R.id.edit_fragment_profile_image_iv))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun nameTextViewsVisible() {
        Espresso.onView(withId(R.id.edit_fragment_profile_name_tv))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.edit_fragment_profile_nameValue_tv))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun emailTextViewsVisible() {
        Espresso.onView(withId(R.id.edit_fragment_profile_email_tv))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.edit_fragment_profile_emailValue_tv))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun squadTextViewsVisible() {
        Espresso.onView(withId(R.id.edit_fragment_profile_squad_tv))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.edit_fragment_profile_squadValue_et))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun stackTextViewsVisible() {
        Espresso.onView(ViewMatchers.withId(R.id.edit_fragment_profile_stack_tv))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.edit_fragment_profile_stackValue_et))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun mobileTextViewsVisible() {
        Espresso.onView(withId(R.id.edit_fragment_profile_mobile_tv))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.edit_fragment_profile_mobileValue_et))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun editTextViewAndIconVisible() {
        Espresso.onView(withId(R.id.edit_fragment_profile_back_tv))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun validateTextField_valid () {

        Espresso.onView(withId(R.id.edit_fragment_profile_squadValue_et))
            .perform(clearText(), typeText("SQ007"), closeSoftKeyboard())
        Espresso.onView(withId(R.id.edit_fragment_profile_stackValue_et))
            .perform(clearText(), typeText("Android"), closeSoftKeyboard())
        Espresso.onView(withId(R.id.edit_fragment_profile_mobileValue_et))
            .perform(clearText(), typeText("08134532444"), closeSoftKeyboard())
    }

}