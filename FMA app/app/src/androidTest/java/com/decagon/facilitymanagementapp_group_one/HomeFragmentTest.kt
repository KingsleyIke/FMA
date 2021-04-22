package com.decagon.facilitymanagementapp_group_one

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.decagon.facilitymanagementapp_group_one.ui.HomeFragment
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class HomeFragmentTest : TestCase() {

    @Before
    override fun setUp(){
        launchFragmentInHiltContainer<HomeFragment>()
    }

    @Test
    fun test_isNoPendingRequestsText(){
        onView(withId(R.id.fragment_home_noPendingRequestMessage_lin)).check(matches(isDisplayed()))
    }

    @Test
    fun test_canTypeInSearchBox(){
        onView(withId(R.id.fragment_home_searchBox_et)).perform(typeText("Just testing the search box"))
    }

    @Test
    fun test_toolbarIsVisible(){
        onView(withId(R.id.fragment_home_actionBar_ab)).check(matches(isDisplayed()))
    }

    @Test
    fun test_addNewRequestBtn_isClickable(){
        onView(withId(R.id.fragment_home_addRequest_fab)).check(matches(isClickable()))
    }

}