package com.decagon.facilitymanagementapp_group_one.ui

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.decagon.facilitymanagementapp_group_one.R
import com.decagon.facilitymanagementapp_group_one.databinding.FragmentHomeBinding
import com.decagon.facilitymanagementapp_group_one.adapters.MyRequestsAdapter
import com.decagon.facilitymanagementapp_group_one.data.AuthDiskStore
import com.decagon.facilitymanagementapp_group_one.model.Feed
import com.decagon.facilitymanagementapp_group_one.utils.*
import com.decagon.facilitymanagementapp_group_one.viewmodel.RequestViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


//@Suppress("DEPRECATION")
@AndroidEntryPoint
class HomeFragment : Fragment(), ItemClickListener {

    private var _binding: FragmentHomeBinding? = null

    @Inject
    lateinit var authDiskStore: AuthDiskStore
    private val adapter = MyRequestsAdapter()

    private val viewModel: RequestViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater)

        adapter.listener = this

        val userData = authDiskStore.getToken()
        viewModel.loadRequests(userData[TOKEN] as String, userData[ID] as String)

        subscribeFeedObserver()
        subscribeDeleteObserver()

        _binding!!.fragmentHomeMyRequestsRv.adapter = adapter

        //Add swipe to delete in recyclerview.
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                AlertDialog.Builder(viewHolder.itemView.context)
                    .setTitle("Alert")
                    .setMessage("Are you sure you want to delete this?")
                    .setPositiveButton("Yes") { _, _ ->
                        val complaintId = adapter.getComplaintId(viewHolder.adapterPosition)
                        viewModel.deleteRequest(userData[TOKEN] as String, complaintId)
                    }.setNegativeButton("Cancel") { _, _ ->
                        adapter.notifyDataSetChanged()
                    }.setCancelable(false)
                    .create()
                    .show()
            }

        }).apply {
            attachToRecyclerView(_binding!!.fragmentHomeMyRequestsRv)
        }

        // Adding vertical lines between two views items on the recyclerView
        _binding!!.fragmentHomeMyRequestsRv.addItemDecoration(
            DividerItemDecoration(
                requireContext(), DividerItemDecoration.VERTICAL
            )
        )

        // What happens when the add new request FAB is clicked
        _binding!!.fragmentHomeAddRequestFab.setOnClickListener {
            findNavController().navigate(R.id.newRequestFragment)
        }
        return _binding!!.root
    }

    /**
     * @param msg message to display in toast.
     */
    private fun showToast(msg: String) =
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()

    /**
     * Observe for live data changes in feeds
     */
    private fun subscribeFeedObserver() {
        viewModel.feeds.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                adapter.loadRequests(it)
                _binding!!.fragmentHomeNoPendingRequestMessageLin.visibility = View.GONE
            } else {
                _binding!!.fragmentHomeNoPendingRequestMessageLin.visibility = View.VISIBLE
            }
        }
    }

    /**
     * Observe for live data changes on delete.
     */
    private fun subscribeDeleteObserver() {
        viewModel.deleteResult.observe(viewLifecycleOwner) { result ->
            if (result) {
                showToast("Successful")
            } else {
                showToast("An error occurred while deleting item")
            }
            adapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onRecyclerViewItemClicked(view: View, feed: Feed, position: Int) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToComplaintDetails(feed.title, feed.question)
        findNavController().navigate(action)
    }
}