package com.decagon.facilitymanagementapp_group_one.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.decagon.facilitymanagementapp_group_one.R
import com.decagon.facilitymanagementapp_group_one.databinding.FragmentFailedAuthorizationBinding
import com.decagon.facilitymanagementapp_group_one.utils.AuthController.mSingleAccountApp
import com.decagon.facilitymanagementapp_group_one.utils.navigate
//import com.decagon.facilitymanagementapp_group_one.utils.mSingleAccountApp
import com.microsoft.identity.client.ISingleAccountPublicClientApplication
import com.microsoft.identity.client.exception.MsalException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [FailedAuthorizationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FailedAuthorizationFragment : Fragment() {

    private var _binding: FragmentFailedAuthorizationBinding? = null
    private lateinit var binding: FragmentFailedAuthorizationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (mSingleAccountApp != null) {
            mSingleAccountApp!!.signOut(object: ISingleAccountPublicClientApplication.SignOutCallback{
                override fun onSignOut() {
                    //
                }

                override fun onError(exception: MsalException) {
                    exception.printStackTrace()
                }

            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /**
         * Inflate the layout for this fragment and set up click listeners
         * on the Up, Try again and Back buttons
         */
        binding = FragmentFailedAuthorizationBinding.inflate(inflater)
        _binding = binding

        binding.fragmentFailedAuthorizationTryAgainBtn
            .setOnClickListener { navigate(R.id.authFragment) }

        binding.fragmentFailedAuthorizationBackBtn
            .setOnClickListener { navigate(R.id.onboardingFragment) }

        binding.fragmentFailedAuthorizationTb
            .setOnClickListener { navigate(R.id.onboardingFragment) }

        return binding.root
    }

    /**
     * Clean up instance of viewBinding
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}