package com.decagon.facilitymanagementapp_group_one

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.decagon.facilitymanagementapp_group_one.ui.feed_categories.FeedsFragment
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class FeedsFragmentTest : TestCase() {
    @Before
    override fun setUp(){
        val scenario =
            launchFragmentInContainer<FeedsFragment>(themeResId = R.style.Theme_FacilityManagementAppGroupOne)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun test_feedsAreDisplayed_inDifferentCategory(){
        onView(withId(R.id.fragment_feeds_tabLayout_tl))
            .check(matches(isDisplayed()))
        onView(ViewMatchers.withText("APARTMENT")).perform(click())
        onView(ViewMatchers.withText("APPLIANCES")).perform(click())
        onView(ViewMatchers.withText("OTHERS")).perform(click())
    }

}