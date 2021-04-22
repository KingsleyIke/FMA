package com.decagon.facilitymanagementapp_group_one

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.decagon.facilitymanagementapp_group_one.ui.ComplaintDetailsFragment
import com.decagon.facilitymanagementapp_group_one.ui.ComplaintDetailsFragmentArgs
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComplaintDetailsFragmentTest {

    @Before
    fun setUpFragment(){
        val title = "Tittle"
        val body = "Some Text"
        val bundle = ComplaintDetailsFragmentArgs(title, body).toBundle()
        launchFragmentInContainer<ComplaintDetailsFragment>(bundle,
            R.style.Theme_FacilityManagementAppGroupOne)
    }
    @Test
    fun test_ComplaintFragment_Visibility(){
        Espresso.onView(withId(R.id.fragment_complaints_details))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun addComment_sendIcon_AddsComments(){
        Espresso.onView(withId(R.id.fragment_complaint_details_add_comments_edtv))
            .perform(ViewActions.typeText("I just made a complaint"))
        Espresso.onView(withId(R.id.fragment_complaint_details_send_icon_iv)).perform(ViewActions.click())
    }

    @Test
    fun test_ComplaintBody_Visibility(){
        Espresso.onView(withId(R.id.fragment_complaint_details_complaint_tv)).check(
            ViewAssertions.matches(
                isDisplayed()
            )
        )
        Espresso.onView(withId(R.id.fragment_complaint_details_complaint_tv)).check(
            ViewAssertions.matches(
                withText("Some Text")
            )
        )
    }

    @Test
    fun test_ComplaintImage_Visibility(){
        Espresso.onView(withId(R.id.fragment_complaint_details_complaint_image_iv)).check(
            ViewAssertions.matches(
                isDisplayed()
            )
        )
    }

    @Test
    fun test_ComplaintMessageIcon_Visibility(){
        Espresso.onView(withId(R.id.fragment_complaint_details_messsage_icon_iv)).check(
            ViewAssertions.matches(
                isDisplayed()
            )
        )
    }

    @Test
    fun test_ComplaintLikeIcon_Visibility(){
        Espresso.onView(withId(R.id.fragment_complaint_details_likes_icon_iv)).check(
            ViewAssertions.matches(
                isDisplayed()
            )
        )
    }

    @Test
    fun test_NumLikesTextview_Visibility(){
        Espresso.onView(withId(R.id.fragment_complaint_details_num_likes_tv)).check(
            ViewAssertions.matches(
                isDisplayed()
            )
        )
        Espresso.onView(withId(R.id.fragment_complaint_details_num_likes_tv)).check(
            ViewAssertions.matches(
                withText(R.string.fragment_complaint_num_likes)
            )
        )
    }

    @Test
    fun test_NumCommentsTextview_Visibility(){
        Espresso.onView(withId(R.id.fragment_complaints_details_num_comments_tv)).check(
            ViewAssertions.matches(
                isDisplayed()
            )
        )
    }

    @Test
    fun test_AddCommentTextView_Visibility(){
        Espresso.onView(withId(R.id.fragment_complaint_details_add_comments_edtv)).check(
            ViewAssertions.matches(
                isDisplayed()
            )
        )
        Espresso.onView(withId(R.id.fragment_complaint_details_add_comments_edtv)).check(
            ViewAssertions.matches(
                withHint(R.string.fragment_comment_details_coment_hint)
            )
        )
    }

    @Test
    fun test_SendIcon_Visibility(){
        Espresso.onView(withId(R.id.fragment_complaint_details_send_icon_iv)).check(
            ViewAssertions.matches(
                isDisplayed()
            )
        )
    }
}
