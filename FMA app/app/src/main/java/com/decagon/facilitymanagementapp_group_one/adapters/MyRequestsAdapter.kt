package com.decagon.facilitymanagementapp_group_one.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.decagon.facilitymanagementapp_group_one.utils.ItemClickListener
import com.decagon.facilitymanagementapp_group_one.R
import com.decagon.facilitymanagementapp_group_one.model.Feed
import kotlinx.android.synthetic.main.requests_item_view_holder.view.*

/**
 * Used to populate the feeds list recyclerView of
 * the users individual requests consisting of
 *  all the feeds categories in this project
 */
class MyRequestsAdapter : RecyclerView.Adapter<MyRequestsAdapter.RequestViewHolder>() {

    private var myRequestsList = emptyList<Feed>()
    var listener: ItemClickListener? = null

    inner class RequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val requestTitle: TextView = itemView.findViewById(R.id.layout_request_item_view_requestTitle_tv)
        val requestTime: TextView = itemView.findViewById(R.id.layout_request_item_view_requestTime_tv)
        val requestBody: TextView = itemView.findViewById(R.id.layout_request_item_view_requestBody_tv)
    }

    /**
     * /**
     * Called when RecyclerView needs a new RecyclerView.ViewHolder
     * of the given type to represent an item
    */
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        return RequestViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.requests_item_view_holder, parent, false)
        )
    }

    /**
     * Called by RecyclerView to display the data at the specified position
     */
    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        holder.requestTitle.text = myRequestsList[position].title
//        holder.requestTime.text = myRequestsList[position].time
        holder.requestBody.text = myRequestsList[position].question
        holder.itemView.layout_request_item_view_lo.setOnClickListener {
            listener?.onRecyclerViewItemClicked(it, myRequestsList[position], position)
        }
    }

    /**
     * Returns the total number of items in the data set held by the adapter
     */
    override fun getItemCount(): Int {
        return myRequestsList.size
    }

    /**
     * Load myRequestsList with data
     */
//    fun addFeed(feed: Feed) {
//        myRequestsList.add(feed)
//        notifyDataSetChanged()
//    }

    fun loadRequests(requests : List<Feed>){
        myRequestsList = requests
        notifyDataSetChanged()
    }

    fun getComplaintId(adapterPosition: Int): String {
        return myRequestsList[adapterPosition].id
    }
}