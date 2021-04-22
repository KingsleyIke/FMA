package com.decagon.facilitymanagementapp_group_one.ui.feed_categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.decagon.facilitymanagementapp_group_one.databinding.FragmentFeedsBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.decagon.facilitymanagementapp_group_one.adapters.FeedsPagerAdapter


class FeedsFragment : Fragment(), LifecycleOwner {

    private var _binding: FragmentFeedsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFeedsBinding.inflate(layoutInflater)

        // Instantiating a viewPager Adapter
        val viewPagerAdapter = FeedsPagerAdapter(childFragmentManager, lifecycle)

        // Assigning an adapter to the viewPager and also making sure the
        // state is not being saved to prevent the app from crashing when
        // the Feeds icon is reselected on the bottom navigation bar
        _binding!!.feedsViewPager.apply {
//            isSaveEnabled = false
            adapter = viewPagerAdapter
        }

        // Connecting the tabLayout to the viewPager using the attach() function
        TabLayoutMediator(
            _binding!!.fragmentFeedsTabLayoutTl,
            _binding!!.feedsViewPager
        ) { tab: TabLayout.Tab, i: Int ->
            when (i) {
                0 -> tab.apply {
                    text = "Food"
                }
                1 -> tab.text = "Apartment"
                2 -> tab.text = "Appliances"
                else -> tab.text = "Others"
            }
        }.attach()

        return _binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}