package com.decagon.facilitymanagementapp_group_one.utils

import android.view.View
import com.decagon.facilitymanagementapp_group_one.model.Feed

interface ItemClickListener {
        fun onRecyclerViewItemClicked(view: View, feed: Feed, position: Int)

}
