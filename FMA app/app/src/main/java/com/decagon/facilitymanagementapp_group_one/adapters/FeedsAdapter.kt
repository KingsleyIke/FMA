package com.decagon.facilitymanagementapp_group_one.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.decagon.facilitymanagementapp_group_one.utils.ItemClickListener
import com.decagon.facilitymanagementapp_group_one.R
import com.decagon.facilitymanagementapp_group_one.model.Feed
import kotlinx.android.synthetic.main.feeds_item_view_holder.view.*

/**
 * Used to populate the feeds list recyclerView of all
 * the feeds categories in this project
 */
class FeedsAdapter : RecyclerView.Adapter<FeedsAdapter.FeedsViewHolder>() {

//    var requests =
    var feedsList = emptyList<Feed>()
    var listener: ItemClickListener? = null

    inner class FeedsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var feed: Feed
        val feedTitle: TextView = itemView.findViewById(R.id.layout_feeds_item_view_holder_feedTitle_tv)
        val feedTime: TextView = itemView.findViewById(R.id.layout_feeds_item_view_holder_feedTime_tv)
        val feedBody: TextView = itemView.findViewById(R.id.layout_feeds_item_view_holder_feedBody_tv)
        val profilePicture: ImageView = itemView.findViewById(R.id.layout_feeds_item_view_holder_profile_pic_civ)

    }

    /**
     * Called when RecyclerView needs a new RecyclerView.ViewHolder
     * of the given type to represent an item
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedsViewHolder {
        return FeedsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.feeds_item_view_holder, parent, false)
        )
    }

    /**
     * Called by RecyclerView to display the data at the specified position
     */
    override fun onBindViewHolder(holder: FeedsViewHolder, position: Int) {
        holder.feedTitle.text = feedsList[position].title
        holder.feedBody.text = feedsList[position].question
//        holder.feedTime.text = feedsList[position].time
//        holder.profilePicture.setImageResource(feedsList[position].image)
        holder.itemView.layout_feeds_item_view_holder_view.setOnClickListener {
            listener?.onRecyclerViewItemClicked(it, feedsList[position], position)
        }
    }

    /**
     * Returns the total number of items in the data set held by the adapter
     */
    override fun getItemCount(): Int {
        return feedsList.size
    }

    /**
     * Load feeds data to the adapter list
     */
    fun loadFeeds(feeds: List<Feed>) {
        feedsList = feeds
    }

//    fun addNewRequest(request : Feed){
//        requests.add(request)
//        notifyDataSetChanged()
//    }

}