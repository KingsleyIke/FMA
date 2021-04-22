package com.decagon.facilitymanagementapp_group_one.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.decagon.facilitymanagementapp_group_one.R
import com.decagon.facilitymanagementapp_group_one.data.AuthDiskStore
import com.decagon.facilitymanagementapp_group_one.databinding.FragmentNewRequestBinding
import com.decagon.facilitymanagementapp_group_one.model.Complaint
import com.decagon.facilitymanagementapp_group_one.model.Feed
import com.decagon.facilitymanagementapp_group_one.utils.Functions.Companion.snackbar
import com.decagon.facilitymanagementapp_group_one.utils.Functions.Companion.timeOfRequest
import com.decagon.facilitymanagementapp_group_one.utils.ID
import com.decagon.facilitymanagementapp_group_one.utils.TOKEN
import com.decagon.facilitymanagementapp_group_one.viewmodel.RequestViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewRequestFragment : Fragment(), AdapterView.OnItemClickListener {

    private var _binding: FragmentNewRequestBinding? = null

    @Inject
    lateinit var authDiskStore: AuthDiskStore

    private val viewModel:RequestViewModel by viewModels()

    private var feedId = ""
    private var type = ""
    private var userId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentNewRequestBinding.inflate(layoutInflater)

        // Create an array adapter for the category spinner
        ArrayAdapter.createFromResource(
            requireContext(), R.array.feed_categories, android.R.layout.simple_spinner_item
        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            _binding!!.fragmentNewRequestCategoriesAt.setAdapter(arrayAdapter)
            _binding!!.fragmentNewRequestCategoriesAt.onItemClickListener = this
        }

        // Returns to the requests home screen
        _binding!!.fragmentNewRequestBackArrowTv.setOnClickListener {
            findNavController().popBackStack()
        }

        // Returns to the requests home screen
        _binding!!.fragmentNewRequestBackBtnBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        // Adds a new posts to the lists of posts in the recyclerView
        _binding!!.fragmentNewRequestSubmitBtnBtn.setOnClickListener {
            createNewRequest()
        }

        viewModel.addResult.observe(viewLifecycleOwner, {
            Log.d("COMPLAINT", it.message)
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
        })

        return _binding!!.root
    }


    /**
     * Creates a new Request object to be added to the server
     */
    private fun createNewRequest() {
        val title = _binding!!.fragmentNewRequestRequestSubjectEt.text.toString()
        val body = _binding!!.fragmentNewRequestDescriptionEt.text.toString()
        val timeOfRequest = timeOfRequest()
        if (isEntriesValid(title, body)) {
            val complaint = Complaint(title,body,type,"",true,
                authDiskStore.getToken()[ID] as String
            )
            viewModel.addNewComplaint(authDiskStore.getToken()[TOKEN] as String,
                complaint, feedId
            )
            findNavController().popBackStack()
        } else {
            showError()
        }
    }

    /**
     * Display an error feedback to user.
     */
    private fun showError() {
        if (feedId.isBlank()) {
            _binding!!.fragmentNewRequestCategoriesSp.apply {
                boxStrokeWidth = 2
                error = " "
            }
        } else {
            _binding!!.fragmentNewRequestCategoriesSp.apply {
                error = null
                boxStrokeWidth = 0
            }
        }
        if (_binding!!.fragmentNewRequestRequestSubjectEt.text!!.isBlank()) {
            _binding!!.fragmentNewRequestSubjectTl.apply {
                boxStrokeWidth = 2
                error = " "
            }
        } else {
            _binding!!.fragmentNewRequestSubjectTl.apply {
                error = null
                boxStrokeWidth = 0
            }
        }
        if (_binding!!.fragmentNewRequestDescriptionEt.text!!.isBlank()) {
            _binding!!.fragmentNewRequestDescriptionTl.apply {
                boxStrokeWidth = 2
                error = " "
            }
        } else {
            _binding!!.fragmentNewRequestDescriptionTl.apply {
                error = null
                boxStrokeWidth = 0
            }
        }
    }

    /**
     * Validate the user entries
     */
    private fun isEntriesValid(title: String, body: String): Boolean {
        return !(title.isBlank() || body.isBlank() || feedId.isBlank())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Assigns the value of the item selected from the spinner to the CATEGORY variable
     */
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(parent?.getItemAtPosition(position) as String){
            "Food" -> {
                feedId = "29cb44af-b662-4f5f-9755-ca68fa21c8ea"
                type = "food"
            }
            "Apartment" -> {
                feedId = "4a89823a-5de0-489f-9e49-0febe8f387c5"
                type = "apartment"
            }
            "Appliances" -> {
                feedId = "bde57728-f881-458d-b697-151f5ac51d11"
                type = "appliances"
            }
            else -> {
                feedId = "1c250683-d543-4089-9444-8fc465a8da9b"
                type = "others"
            }
        }
    }
}