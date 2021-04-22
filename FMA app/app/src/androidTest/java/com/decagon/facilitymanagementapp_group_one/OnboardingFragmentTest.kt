package com.decagon.facilitymanagementapp_group_one

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.decagon.facilitymanagementapp_group_one.ui.OnboardingFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OnboardingFragmentTest{

    @Before
    fun setUp() {
        launchFragmentInHiltContainer<OnboardingFragment>()
    }

    @Test
    fun test_isOnboardingFragmentScreen_Visible(){
        onView(withId(R.id.onboarding_fragment)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isApplicationTitle_Displayed(){
        onView(withId(R.id.fragment_onboarding_app_title_tv)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isApplication_OnboardingImage_Displayed(){
        onView(withId(R.id.fragment_onboarding_title_image_iv)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isApplicationSubtitle_Displayed(){
        onView(withId(R.id.fragment_onboarding_app_subtitle_tv)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isGetStartedBtn_Displayed(){
        onView(withId(R.id.fragment_onboarding_get_started_btn)).check(matches(isDisplayed()))
    }

}