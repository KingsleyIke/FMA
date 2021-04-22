package com.decagon.facilitymanagementapp_group_one.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.decagon.facilitymanagementapp_group_one.ui.feed_categories.ApartmentFragment
import com.decagon.facilitymanagementapp_group_one.ui.feed_categories.AppliancesFragment
import com.decagon.facilitymanagementapp_group_one.ui.feed_categories.FoodFragment
import com.decagon.facilitymanagementapp_group_one.ui.feed_categories.OthersFragment

class FeedsPagerAdapter (fragment: FragmentManager, lifecycle: Lifecycle)
    : FragmentStateAdapter(fragment,lifecycle) {

    /**
     * Returns the total number of items in the data set held by the adapter
     */
    override fun getItemCount(): Int {
        return 4
    }

    /**
     * Provide a new Fragment associated with the specified position
     */
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> FoodFragment()
            1 -> ApartmentFragment()
            2 -> AppliancesFragment()
            else -> OthersFragment()
        }
    }
}