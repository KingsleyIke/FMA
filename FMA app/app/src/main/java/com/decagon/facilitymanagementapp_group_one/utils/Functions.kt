package com.decagon.facilitymanagementapp_group_one.utils

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.net.Uri
import android.provider.OpenableColumns
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.decagon.facilitymanagementapp_group_one.R
import com.decagon.facilitymanagementapp_group_one.model.Feed
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

/**
 * This class contains all the extra methods
 * that are need for the app to function
 */
class Functions {
    companion object{
        /**
         * Filters the list of post by their categories
         */
        fun filterFeedCategory(feeds: List<Feed>, category: String): List<Feed> {
            return feeds.filter {
                it.type == category
            }
        }

        /**
         * Formats the current time of the post
         * at the time of post
         */
        @SuppressLint("SimpleDateFormat")
        fun timeOfRequest() : String{
            val dateFormat = SimpleDateFormat("dd/MM/yyyy")
            return dateFormat.format(Date())
        }

        /**
         * Extension function to display a snackbar after
         * an image upload
         */
        fun View.snackbar(message : String){
            Snackbar.make(
                this,
                message,
                Snackbar.LENGTH_LONG).also { snackbar ->
                    snackbar.setAction("Ok"){
                        snackbar.dismiss()
                    }
            }.show()
        }

        /**
         *
         */
        fun ContentResolver.getFileName(uri : Uri) : String {
            var name = ""
            val cursor = query(uri,null,null,null,null)
            cursor?.use {
                it.moveToFirst()
                name = cursor.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))
            }
            return name
        }

        fun loadWithGlide(fragment: Fragment, imgView: ImageView, imgUrl: String) {
            Glide.with(fragment)
                .load(imgUrl)
                .placeholder(R.drawable.loading_animation)
                .into(imgView);
        }
    }
}