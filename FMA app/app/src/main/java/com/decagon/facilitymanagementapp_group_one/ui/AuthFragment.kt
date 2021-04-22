package com.decagon.facilitymanagementapp_group_one.ui

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.decagon.facilitymanagementapp_group_one.R
import com.decagon.facilitymanagementapp_group_one.data.AuthDiskStore
import com.decagon.facilitymanagementapp_group_one.data.ResponseBody
import com.decagon.facilitymanagementapp_group_one.databinding.FragmentAuthBinding
import com.decagon.facilitymanagementapp_group_one.model.AuthResponse
import com.decagon.facilitymanagementapp_group_one.utils.*
import com.decagon.facilitymanagementapp_group_one.utils.AuthController.mSingleAccountApp
import com.decagon.facilitymanagementapp_group_one.viewmodel.AuthViewModel
import com.microsoft.identity.client.AuthenticationCallback
import com.microsoft.identity.client.IAuthenticationResult
import com.microsoft.identity.client.exception.MsalClientException
import com.microsoft.identity.client.exception.MsalException
import com.microsoft.identity.client.exception.MsalServiceException
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response
import javax.inject.Inject

/**
 * @author Francis Akpan
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class AuthFragment : Fragment(), AuthenticationCallback {
    private val TAG = AuthFragment::class.java.simpleName

    private var mail = ""
    private var displayName = ""

    private var binding: FragmentAuthBinding? = null

    private val viewModel: AuthViewModel by viewModels()

    @Inject lateinit var authDiskStore: AuthDiskStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Perform signin operations.
        if (mSingleAccountApp != null) {
            mSingleAccountApp!!.signIn(
                activity as Activity,
                "",
                arrayOf("user.read"),
                this
            )
        } else {
            AuthController.initialAuth(requireContext(), findNavController())
            Log.d(TAG, "null")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
    }

    /**
     * SubScribe to observe livedata changes from viewModel
     */
    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner) { dataState ->
            when (dataState) {
                is DataState.Error -> {
                    navigate(R.id.failedAuthorizationFragment)
                    Log.d(TAG, "An error occurred!")
                }
                is DataState.Success -> {
                    hideLoadingIndicatorAndText()
                    val result = dataState.data
                    Log.d(TAG, result.toString())
                    saveToDisk(result)
                    viewModel.getUserProfile("Bearer " + authDiskStore.getToken()[TOKEN] as String,
                        authDiskStore.getToken()[ID] as String)
                    showAlertDialog(displayName)
                }
                DataState.Waiting -> {
                    Log.d(TAG, "Waiting for response")
                }
            }
        }

        viewModel.result.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Error -> {
                    Log.d(TAG, "Error fetching user data!")
                }
                is DataState.Success -> {
                    authDiskStore.saveUserProfile(it.data)
                    Log.d("Profile", it.data.toString())
                }
                DataState.Waiting -> {
                    Log.d(TAG, "Waiting for response")
                }
            }
        }
    }



    private fun saveToDisk(response: AuthResponse) {
        authDiskStore.saveToken(
            response.data.id,
            response.data.token,

            response.data.isProfileCompleted
        )
        authDiskStore.saveUser(displayName, mail)
    }

    /**
     * On signin success, make api request to the backend to get user details
     */
    override fun onSuccess(authenticationResult: IAuthenticationResult?) {
        if (authenticationResult?.account != null) {
            //callGraphAPI(authenticationResult)
            Log.i("TOKEN", authenticationResult.accessToken )
            viewModel.attemptAuth(authenticationResult.accessToken)
        }
    }

    /**
     * On sign in error.
     */
    override fun onError(exception: MsalException?) {
        /* Failed to acquireToken */
        Log.d(TAG, "Authentication failed: $exception")
        navigate(R.id.failedAuthorizationFragment)
        if (exception is MsalClientException) {
            /* Exception inside MSAL, more info inside MsalError.java */
        } else if (exception is MsalServiceException) {
            /* Exception when communicating with the STS, likely config issue */
        }
    }

    /**
     * On cancel signin, navigate back to onBoarding fragment
     */
    override fun onCancel() {
        /* com.decagon.facilitymanagementapp_group_one.model.User canceled the authentication */
        Log.d(TAG, "com.decagon.facilitymanagementapp_group_one.model.User cancelled login.")
        navigate(R.id.onboardingFragment)
    }

    /**
     * Hide loading indicator.
     */
    private fun hideLoadingIndicatorAndText() {
        binding!!.loadingIndicator.visibility = View.GONE
        binding!!.textView.visibility = View.GONE
    }


    /**
     * show AlertDialog on successful authentication.
     */
    private fun showAlertDialog(displayName: String?) {
        AlertDialog.Builder(requireContext())
            .setTitle("Success")
            .setMessage(
                String.format(
                    getString(R.string.fragment_auth_alert_dialog_text),
                    displayName
                )
            ).setPositiveButton("OK") { dialog, _ ->
                if (!(authDiskStore.getToken()[IS_PROFILE_COMPLETED] as Boolean)){
                    navigate(R.id.profileFragment)
                    dialog.dismiss()
                }else{
                    navigate(R.id.homeFragment)
                    dialog.dismiss()
                }

            }.setOnCancelListener {
                navigate(R.id.homeFragment)
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}