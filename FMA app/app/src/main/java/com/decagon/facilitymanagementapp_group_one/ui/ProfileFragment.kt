package com.decagon.facilitymanagementapp_group_one.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import com.decagon.facilitymanagementapp_group_one.data.AuthDiskStore
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.decagon.facilitymanagementapp_group_one.R
import com.decagon.facilitymanagementapp_group_one.databinding.FragmentProfileBinding
import com.decagon.facilitymanagementapp_group_one.model.UserProfile
import com.decagon.facilitymanagementapp_group_one.utils.*
import com.decagon.facilitymanagementapp_group_one.viewmodel.ProfileViewModel
import com.microsoft.identity.client.ISingleAccountPublicClientApplication
import com.microsoft.identity.client.exception.MsalException

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding
        get() = _binding!!

    @Inject
    lateinit var authDiskStore: AuthDiskStore

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateUserProfile()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.fragmentProfileEditTv.setOnClickListener {
            findNavController().navigate(R.id.editProfileFragment)
        }

        binding.fragmentProfileLogOutBtn.setOnClickListener {
            AuthController.mSingleAccountApp?.signOut(object :
                ISingleAccountPublicClientApplication.SignOutCallback {
                override fun onSignOut() {
//                    findNavController().popBackStack()
                    findNavController().navigate(R.id.onboardingFragment)
                }
                override fun onError(exception: MsalException) {
                }
            })
        }

        return binding.root
    }

    //items provided in the shared preference is fetched here
    @SuppressLint("SetTextI18n")
    private fun populateUserProfile() {
        binding.fragmentProfileDisplayNameHeaderTv.text =
            authDiskStore.getUserProfile()[USER_DISPLAY_NAME]
        binding.fragmentProfileStacKSquadTv.text =
            "${authDiskStore.getUserProfile()[STACK]} - ${authDiskStore.getUserProfile()[SQUAD]}"
        binding.fragmentProfileNameValueTv.text = authDiskStore.getUserProfile()[USER_DISPLAY_NAME]
        binding.fragmentProfileEmailValueTv.text = authDiskStore.getUserProfile()[USER_MAIL]
        binding.fragmentProfileSquadValueTv.text = authDiskStore.getUserProfile()[SQUAD]
        binding.fragmentProfileStackValueTv.text = authDiskStore.getUserProfile()[STACK]
        binding.fragmentProfileMobileValueTv.text = authDiskStore.getUserProfile()[MOBILE]
        //loadWithGlide(this,binding.fragmentProfileImage,user.imageUrl.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}