package com.decagon.facilitymanagementapp_group_one.ui

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.decagon.facilitymanagementapp_group_one.R
import com.decagon.facilitymanagementapp_group_one.data.AuthDiskStore
import com.decagon.facilitymanagementapp_group_one.databinding.ActivityMainBinding
import com.decagon.facilitymanagementapp_group_one.utils.AuthController
import com.decagon.facilitymanagementapp_group_one.utils.PROFILE_PREF
import com.microsoft.identity.client.ISingleAccountPublicClientApplication
import com.microsoft.identity.client.exception.MsalException
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var backPressedOnce = false
    private lateinit var navController: NavController

    @Inject
    lateinit var authDiskStore: AuthDiskStore

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val navHost =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment)) as NavHostFragment
        navController = navHost.navController
        AuthController.initialAuth(applicationContext, navController)

        // Setting up the bottom navigation with navController
        binding!!.activityMainBottomNavigationViewBn.setupWithNavController(navController)

        // Determines the fragments where the bottom navigation will be visible
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> binding!!.activityMainBottomNavigationViewBn
                    .visibility = View.VISIBLE
                R.id.feedsFragment -> binding!!.activityMainBottomNavigationViewBn
                    .visibility = View.VISIBLE
                R.id.profileFragment -> binding!!.activityMainBottomNavigationViewBn
                    .visibility = View.VISIBLE
                R.id.editProfileFragment -> binding!!.activityMainBottomNavigationViewBn
                    .visibility = View.VISIBLE
                else -> binding!!.activityMainBottomNavigationViewBn
                    .visibility = View.GONE
            }
        }
    }

    override fun onBackPressed() {
        when (navController.currentDestination) {
            navController.graph.findNode(R.id.homeFragment) -> {
                if (backPressedOnce) {
                    AuthController.mSingleAccountApp?.signOut(object :
                        ISingleAccountPublicClientApplication.SignOutCallback {
                        override fun onSignOut() {
                            finish()
                        }

                        override fun onError(exception: MsalException) {
//                            TODO("Not yet implemented")
                        }

                    })
                    return
                }

                backPressedOnce = true
                Toast.makeText(this, "Press BACK again to exit", Toast.LENGTH_SHORT).show()

                Handler().postDelayed({ backPressedOnce = false }, 2000)

            }
            navController.graph.findNode(R.id.authFragment) -> {
                navController.popBackStack()
                navController.navigate(R.id.onboardingFragment)
            }
            navController.graph.findNode(R.id.failedAuthorizationFragment) -> {
                navController.popBackStack()
                navController.navigate(R.id.onboardingFragment)
            }
            navController.graph.findNode(R.id.onboardingFragment) -> {
                finish()
            }

            else -> {
                super.onBackPressed()
            }
        }
    }

    override fun onDestroy() {
        AuthController.mSingleAccountApp?.signOut(object :
            ISingleAccountPublicClientApplication.SignOutCallback {
            override fun onSignOut() {
            }

            override fun onError(exception: MsalException) {
            }
        })
        super.onDestroy()
    }

}
