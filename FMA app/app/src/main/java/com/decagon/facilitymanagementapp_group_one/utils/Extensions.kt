package com.decagon.facilitymanagementapp_group_one.utils

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun Fragment.navigate(@IdRes id: Int) {
    findNavController().popBackStack()
    findNavController().navigate(id)
}