package com.decagon.facilitymanagementapp_group_one.utils

import android.content.Context
import android.util.Log
import androidx.annotation.IdRes
import androidx.navigation.NavController
import com.decagon.facilitymanagementapp_group_one.R
import com.microsoft.identity.client.IAccount
import com.microsoft.identity.client.IPublicClientApplication
import com.microsoft.identity.client.ISingleAccountPublicClientApplication
import com.microsoft.identity.client.PublicClientApplication
import com.microsoft.identity.client.exception.MsalException

/**
 * @author Francis Akpan
 */
object AuthController {
    private val TAG = AuthController::class.java.simpleName

    var mSingleAccountApp: ISingleAccountPublicClientApplication? = null

    fun initialAuth(context: Context, navController: NavController) {
        // Creates a PublicClientApplication object with res/raw/auth_config_single_account.json
        PublicClientApplication.createSingleAccountPublicClientApplication(
            context.applicationContext,
            R.raw.auth_config_file,
            object : IPublicClientApplication.ISingleAccountApplicationCreatedListener {
                override fun onCreated(application: ISingleAccountPublicClientApplication?) {
                    /**
                     * This test app assumes that the app is only going to support one account.
                     * This requires "account_mode" : "SINGLE" in the config json file.
                     *
                     */
                    mSingleAccountApp = application
                    loadAccount(navController)
                }

                override fun onError(exception: MsalException?) {
                    Log.e(TAG, exception.toString())
                }

            }
        )
    }

    /**
     * Load the currently signed-in account, if there's any.
     * If the account is removed the device, the app can also perform the clean-up work in onAccountChanged().
     */
    private fun loadAccount(navController: NavController) {
        if (mSingleAccountApp == null) {
            return
        }

        mSingleAccountApp!!.getCurrentAccountAsync(object :
            ISingleAccountPublicClientApplication.CurrentAccountCallback {
            override fun onAccountLoaded(activeAccount: IAccount?) {
//                if (activeAccount != null) {
//                    navigate(R.id.homeFragment, navController)
//                } else {
//                    navigate(R.id.onboardingFragment, navController)
//                }
                    navigate(R.id.onboardingFragment, navController)
            }

            override fun onAccountChanged(priorAccount: IAccount?, currentAccount: IAccount?) {
                if (currentAccount == null) {
                    navigate(R.id.onboardingFragment, navController)
                }
            }

            override fun onError(exception: MsalException) {
//                exception.message?.let { showToast(it) }
                navigate(R.id.onboardingFragment, navController)
            }
        })
    }

    /**
     * @param id fragment id to set as startDestination
     * Set what fragment to display when app is lauched.
     */
    private fun navigate(@IdRes id: Int, navController: NavController) {
        val inflater = navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)
        graph.startDestination = id

        navController.graph = graph
    }
}