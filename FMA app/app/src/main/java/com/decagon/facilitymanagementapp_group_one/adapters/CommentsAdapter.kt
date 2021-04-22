package com.decagon.facilitymanagementapp_group_one.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.decagon.facilitymanagementapp_group_one.Modelclass
import com.decagon.facilitymanagementapp_group_one.R
import kotlinx.android.synthetic.main.single_comment_sample.view.*

class CommentsAdapter: RecyclerView.Adapter <CommentsAdapter.ViewHolder>() {

    var comments = mutableListOf<Modelclass>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_comment_sample, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.single_comment_name_tv.text = comments[position].name
        holder.itemView.single_comment_time_tv.text = comments[position].time
        holder.itemView.single_comment_comment_tv.text = comments[position].comment
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    }

    fun setupComments(complaint : List<Modelclass>){
        this.comments = complaint as MutableList<Modelclass>
    }
}