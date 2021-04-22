package com.decagon.facilitymanagementapp_group_one.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.decagon.facilitymanagementapp_group_one.Modelclass
import com.decagon.facilitymanagementapp_group_one.adapters.CommentsAdapter
import com.decagon.facilitymanagementapp_group_one.databinding.FragmentComplaintDetailsBinding
import com.decagon.facilitymanagementapp_group_one.viewmodel.CommentsViewModel

class ComplaintDetailsFragment: Fragment() {

    private var _binding: FragmentComplaintDetailsBinding? = null
    private val binding
        get() = _binding!!
    lateinit var commentsAdapter: CommentsAdapter
    lateinit var viewModel: CommentsViewModel

    private val args by navArgs<ComplaintDetailsFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentComplaintDetailsBinding.inflate(inflater, container, false)

        //Sets the tittle of the page
            binding.fragmentCompliantDetailsToolBarTitleTv.text = args.feedTitleNullable
            binding.fragmentComplaintDetailsComplaintTv.text = args.feedBodyNullable

        viewModel = ViewModelProvider(this).get(CommentsViewModel::class.java)

        binding.fragmentComplaintsDetailsBackImgIv.setOnClickListener {
            findNavController().popBackStack()
        }

        initRecyclerView()
        loadComment()
        addCommentClicked()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Initializing recycler view and adapter
     */

    private fun initRecyclerView() {
        binding?.fragmentComplaintDetailsRecyclerviewRv?.apply {
            layoutManager = LinearLayoutManager(context)
            commentsAdapter = CommentsAdapter()
            adapter = commentsAdapter
        }
    }

    /**
     * Populates the comments adapter with dummy data
     * Sets Number of comments textview to the size of comments
     */
    private fun loadComment() {
        viewModel.loadComment()
        viewModel.comments.observe(viewLifecycleOwner, Observer<List<Modelclass>> {
            if (it != null) {
                commentsAdapter.setupComments(it)
                commentsAdapter.notifyDataSetChanged()
                binding?.fragmentComplaintsDetailsNumCommentsTv?.text = it.size.toString()

            }
        })
    }

    /**
     * Adds new comments to recyclerView with data from textview
     * Sets Number of comments textview to the size of comments
     */

    private fun addComment(newComment: Modelclass) {
        viewModel.addComments(newComment)
        viewModel.comments.observe(viewLifecycleOwner, Observer<List<Modelclass>> {
            if (it != null) {
                commentsAdapter.setupComments(it)
                commentsAdapter.notifyDataSetChanged()
                binding?.fragmentComplaintsDetailsNumCommentsTv?.text = it.size.toString()
            }
        })
    }


    /**
     * Calls the addComments Function
     * updates the function with data from edittext
     * removes keyboard after comment has been sent
     */
    private fun addCommentClicked() {
        binding!!.fragmentComplaintDetailsSendIconIv.setOnClickListener {
            val comment = Modelclass(
                "John Doe",
                "Just now",
                binding!!.fragmentComplaintDetailsAddCommentsEdtv.text.toString()
            )
            addComment(comment)
            binding!!.fragmentComplaintDetailsAddCommentsEdtv.text.clear()

            val hideKboard = binding!!.fragmentComplaintDetailsAddCommentsEdtv.context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hideKboard.hideSoftInputFromWindow(
                binding!!.fragmentComplaintDetailsAddCommentsEdtv.windowToken,
                0
            )
        }
    }
}