package com.decagon.facilitymanagementapp_group_one.ui.feed_categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.decagon.facilitymanagementapp_group_one.utils.ItemClickListener
import com.decagon.facilitymanagementapp_group_one.adapters.FeedsAdapter
import com.decagon.facilitymanagementapp_group_one.databinding.FragmentAppliancesBinding
import com.decagon.facilitymanagementapp_group_one.model.DataSource
import com.decagon.facilitymanagementapp_group_one.model.Feed
import com.decagon.facilitymanagementapp_group_one.utils.Functions.Companion.filterFeedCategory

class AppliancesFragment : Fragment(), ItemClickListener {

    private var _binding: FragmentAppliancesBinding? = null
    private val adapter = FeedsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAppliancesBinding.inflate(layoutInflater)

        adapter.listener = this

        // Load feeds data into the recyclerView
        adapter.loadFeeds(filterFeedCategory(DataSource.feedsList, "Appliances"))

        // Attach a recyclerView adapter to the recyclerView
        _binding!!.fragmentAppliancesFeedsListRv.feedsList.adapter = adapter

        // Adding vertical lines between two views items on the recyclerView
        _binding!!.fragmentAppliancesFeedsListRv.feedsList.addItemDecoration(
            DividerItemDecoration(
                requireContext(), DividerItemDecoration.VERTICAL
            )
        )

        return _binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onRecyclerViewItemClicked(view: View, feed: Feed, position: Int) {
        val action = FeedsFragmentDirections.actionFeedsFragmentToComplaintDetails(feed.title, feed.question.toString())
        findNavController().navigate(action)
    }
}