package com.ethan.disneycodechallenge.feature.guest_selection.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ethan.disneycodechallenge.domain.model.GuestModel

class GuestAdapter(private val itemCheckboxCallback: ((GuestModel, Boolean) -> Unit)? = null) :
    ListAdapter<GuestModel, RecyclerView.ViewHolder>(GuestDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GuestItemVH.onCreateViewHolder(parent, itemCheckboxCallback)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItem(position)) {
            is GuestModel -> {
                (holder as GuestItemVH).onBind(model = getItem(position) as GuestModel)
            }
        }
    }
}

class GuestDiffCallback : DiffUtil.ItemCallback<GuestModel>() {

    override fun areItemsTheSame(
        oldItem: GuestModel,
        newItem: GuestModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: GuestModel,
        newItem: GuestModel
    ): Boolean {
        return oldItem.name == newItem.name
    }
}
