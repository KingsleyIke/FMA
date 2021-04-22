package com.decagon.facilitymanagementapp_group_one.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.decagon.facilitymanagementapp_group_one.R
import com.decagon.facilitymanagementapp_group_one.data.AuthDiskStore
import com.decagon.facilitymanagementapp_group_one.data.UploadRequestBody
import com.decagon.facilitymanagementapp_group_one.databinding.FragmentEditProfileBinding
import com.decagon.facilitymanagementapp_group_one.databinding.UploadInProgressLayoutBinding
import com.decagon.facilitymanagementapp_group_one.model.User
import com.decagon.facilitymanagementapp_group_one.model.UserData
import com.decagon.facilitymanagementapp_group_one.model.UserProfile
import com.decagon.facilitymanagementapp_group_one.model.mapToUser
import com.decagon.facilitymanagementapp_group_one.utils.*
import com.decagon.facilitymanagementapp_group_one.viewmodel.ProfileViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EditProfileFragment : Fragment(), UploadRequestBody.UploadCallback {

    @Inject
    lateinit var validate: EditValidation

    @Inject
    lateinit var authDiskStore: AuthDiskStore

    //    lateinit var profileUpdateViewModel: ProfileUpdateViewModel
    private val viewModel by viewModels<ProfileViewModel>()

    private var imageUrl: String = ""
    lateinit var dialog: AlertDialog

    private var selectedImage: Uri? = null

    private var dbinding: UploadInProgressLayoutBinding? = null

    private var _binding: FragmentEditProfileBinding? = null
    private val binding
        get() = _binding!!

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts
            .StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            selectedImage = result.data?.data
            dialog.show()
            //uploadImage()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)

        // Initializing the pending upload dialog box
        dialog = startUploadingDialog()

        //Populate Ui With Data from Shared preference
        populateView()

        // Calling field validation function  and passing in values
        validationSquad(
            binding.editFragmentProfileSquadValueEt.text.toString(),
            binding.editFragmentProfileSquadValueEt
        )
        validationMobile(
            binding.editFragmentProfileMobileValueEt.text.toString(),
            binding.editFragmentProfileMobileValueEt
        )
        validationStack(
            binding.editFragmentProfileStackValueEt
        )


        //Onclick listener to update user profile details
        binding.editFragmentProfileEditBtn.setOnClickListener {

            if (!validate.mobileValidate(binding.editFragmentProfileMobileValueEt.text.toString()) &&
                !validate.squadValidation(binding.editFragmentProfileSquadValueEt.text.toString()) &&
                !validate.squadValidation(binding.editFragmentProfileStackValueEt.text.toString())
            ) {

                Toast.makeText(context, "Fill Fields Correctly", Toast.LENGTH_SHORT).show()
            } else {
                val token = "Bearer " + authDiskStore.getToken()[TOKEN] as String
                val userSquad = binding.editFragmentProfileSquadValueEt.text.toString()
                val userStack = binding.editFragmentProfileStackValueEt.text.toString()
                val userMobile = binding.editFragmentProfileMobileValueEt.text.toString()

                val userData = UserData(
                    authDiskStore.getUserProfile()[USER_FIRST_NAME],
                    authDiskStore.getUserProfile()[USER_LAST_NAME]!!,
                    authDiskStore.getUserProfile()[USER_NAME],
                    userStack,
                    true,
                    true,
                    avatarUrl = null,
                    userSquad,
                    userMobile,
                    null,
                    null,
                )

                val userUpdate =
                    UserProfile(true, userData, "User gotten")

                //Storing the new items in a shared preference

                authDiskStore.saveUserProfile(userUpdate)

                //Forming the request body for the update endpoint
                val user = User(
                    firstName = authDiskStore.getUserProfile()[USER_FIRST_NAME],
                    lastName = authDiskStore.getUserProfile()[USER_LAST_NAME],
                    userName = authDiskStore.getUserProfile()[USER_MAIL],
                    gender = userStack,
                    squad = userSquad,
                    phoneNumber = userMobile
                )

                viewModel.updateUserProfile(user, token)
                findNavController().popBackStack()
            }
        }

        val imgView = binding.editFragmentProfileImageIv

        binding.editFragmentProfileBackTv.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.editFragmentProfileImageIv.setOnClickListener {
            openImageChooser()
        }

        // Observe the response body coming from the server after
        // a successful or unsuccessful upload
//        viewModel.responseBody.observe(viewLifecycleOwner, {
//
//            if (it == null) {
//                Toast.makeText(requireContext(), "Unable to upload image", Toast.LENGTH_LONG)
//                    .show()
//                dialog.dismiss()
//            } else {
//                if (it.success) {
//                    Log.d("IMG_URL", "$it")
//                    imageUrl = it.data.url
//                    binding.editProfileFragment.snackbar(it.message)
//                    loadWithGlide(this,imgView,it.data.url)
//                    authDiskStore.saveUser(imageUrl = it.data.url)
//                    dialog.dismiss()
//                } else {
//                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
//                    dialog.dismiss()
//                }
//            }
//        })

        subscribeObservers()

        return binding.root
    }

    /**
     * SubScribe to observe livedata changes from viewModel
     */
    private fun subscribeObservers() {
        viewModel.response.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Error -> {
                    Log.d("UpdateError", "An error occurred! ${it.exception.localizedMessage}")
                }
                is DataState.Success -> {
                    val responseCode = it.data.code().toString()
                    if (responseCode == "204") {
                        Snackbar.make(
                            requireView(),
                            "Your profile was updated successfully!",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        findNavController().popBackStack()
                        findNavController().navigate(R.id.profileFragment)
                    }
                }
                DataState.Waiting -> {
                    Log.d("WaitingProfile", "Waiting for response")
                }
            }
        }
    }

    /**
     * Update Views function
     */

    @SuppressLint("SetTextI18n")
    fun populateView() {
        //loadWithGlide(this, binding.editFragmentProfileImageIv,user.imageUrl.toString())
        binding.editFragmentProfileNameTitleTv.text =
            authDiskStore.getUserProfile()[USER_DISPLAY_NAME]
        binding.editFragmentProfileStackTitleTv.text =
            "${authDiskStore.getUserProfile()[STACK]} - ${authDiskStore.getUserProfile()[SQUAD]}"
        binding.editFragmentProfileNameValueTv.text =
            authDiskStore.getUserProfile()[USER_DISPLAY_NAME]
        binding.editFragmentProfileEmailValueTv.text = authDiskStore.getUserProfile()[USER_MAIL]
        binding.editFragmentProfileSquadValueEt.setText(authDiskStore.getUserProfile()[SQUAD])
        binding.editFragmentProfileStackValueEt.setText(authDiskStore.getUserProfile()[STACK])
        binding.editFragmentProfileMobileValueEt.setText(authDiskStore.getUserProfile()[MOBILE])
    }

    /**
     * On text change listener to track real time validation of fields
     */

    private fun validationMobile(s: String, txt: EditText) {
        txt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (validate.mobileValidate(s.toString())) {
                } else {
                    txt.error = "Fill Fields Correctly"
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    /**
     * On text change listener to track real time validation of fields
     */
    private fun validationSquad(s: String, txt: EditText) {
        txt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (validate.squadValidation(s.toString())) {
                } else {
                    txt.error = "Fill Fields Correctly"
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    /**
     * On text change listener to track real time validation of fields
     */
    private fun validationStack(txt: EditText) {
        txt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (validate.stackValidation(s.toString())) {
                } else {
                    txt.error = "Fill Fields Correctly"
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun openImageChooser() {
        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"
            val types = arrayOf("image/jpeg", "image/png")
            it.putExtra(Intent.EXTRA_MIME_TYPES, types)
            resultLauncher.launch(it)
        }
    }

    /**
     * Get the image as a file, upload it to the server which
     * returns a responseBody
     */
//    private fun uploadImage() {
//        if (selectedImage == null) {
//            binding.editProfileFragment.snackbar("Please select an image")
//            return
//        }
//
//        // Get the location of actual address of the file
//        val parcelFileDescriptor =
//            activity?.contentResolver?.openFileDescriptor(
//                selectedImage!!, "r", null
//            ) ?: return
//
//        // create a file
//        val file =
//            File(activity?.cacheDir, activity?.contentResolver!!.getFileName(selectedImage!!))
//
//        // Get the file path from the external storage and
//        // create a copy of it in the app specific storage
//        val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
//        val outputStream = FileOutputStream(file)
//        inputStream.copyTo(outputStream)
//
//        // Get the
//        val body = UploadRequestBody(file, "image", this)
//        viewModel.uploadImage(
//            MultipartBody.Part.createFormData("image", file.name, body),
//            "Bearer " + authDiskStore.getToken()[TOKEN] as String
//        )
//    }

    override fun onProgressUpdate(percentage: Int) {
//        TODO("Not yet implemented")
    }

    /**
     * Opens a loading dialog box pending a successful
     * response from the server
     */
    private fun startUploadingDialog(): AlertDialog {
        val loadingDialog = AlertDialog.Builder(requireContext())
        dbinding = UploadInProgressLayoutBinding.inflate(layoutInflater)
        loadingDialog.setView(dbinding!!.root)
        return loadingDialog.create()
    }

    override fun onDestroy() {
        _binding = null
        dbinding = null
        super.onDestroy()
    }

    /**
     * Uses glide library to set profile image
     */


}