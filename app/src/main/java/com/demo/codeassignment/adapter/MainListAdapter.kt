package com.demo.codeassignment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.demo.codeassignment.common.Utility
import com.demo.codeassignment.data.models.UserDetailsModel
import com.demo.codeassignment.databinding.ListItemBinding

class MainListAdapter():  PagingDataAdapter<UserDetailsModel, MainListAdapter.ViewHolder>(PhotosComparator) {
    var mEventListener: EventListnear? = null
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { photoslist ->
            holder.view.tvName.text = photoslist.first_name
            holder.view.tvDescriptions.text = photoslist.email
            Utility.setImageUrl(holder.view.imgPhotos,photoslist.avatar?:"")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    class ViewHolder(val view: ListItemBinding): RecyclerView.ViewHolder(view.root) {

    }

    object PhotosComparator: DiffUtil.ItemCallback<UserDetailsModel>() {
        override fun areItemsTheSame(oldItem: UserDetailsModel, newItem: UserDetailsModel): Boolean {
            // Id is unique.
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: UserDetailsModel, newItem: UserDetailsModel): Boolean {
            return oldItem == newItem
        }
    }

    interface EventListnear {
        fun onItemClicked(title: String,url: String)
    }
}