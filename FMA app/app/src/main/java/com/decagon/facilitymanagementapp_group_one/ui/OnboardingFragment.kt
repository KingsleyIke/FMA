package com.decagon.facilitymanagementapp_group_one.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.decagon.facilitymanagementapp_group_one.R
import com.decagon.facilitymanagementapp_group_one.databinding.FragmentOnboardingBinding
import com.decagon.facilitymanagementapp_group_one.utils.AuthController
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [OnboardingFragment]
 */
@AndroidEntryPoint
class OnboardingFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Navigate to authentication fragment
        binding.fragmentOnboardingGetStartedBtn.setOnClickListener {
            if (AuthController.mSingleAccountApp == null) {
                return@setOnClickListener
            }

            findNavController().popBackStack() /* Close onBoarding fragment. */
            findNavController().navigate(R.id.authFragment)
        }
    }
}