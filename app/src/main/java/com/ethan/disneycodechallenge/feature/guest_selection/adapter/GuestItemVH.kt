package com.ethan.disneycodechallenge.feature.guest_selection.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ethan.disneycodechallenge.databinding.LayoutGuestItemBinding
import com.ethan.disneycodechallenge.domain.model.GuestModel

class GuestItemVH constructor(
    private val binding: LayoutGuestItemBinding,
    private val itemCheckboxCallback: ((GuestModel, Boolean) -> Unit)? = null,
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(model: GuestModel) {
        binding.checkboxGuestName.text = model.name
        binding.checkboxGuestName.contentDescription = model.name
        binding.checkboxGuestName.setOnCheckedChangeListener { compoundButton, checked ->
            itemCheckboxCallback?.invoke(model, checked)
        }
    }

    companion object {
        fun onCreateViewHolder(
            parent: ViewGroup,
            itemCheckboxCallback: ((GuestModel, Boolean) -> Unit)? = null
        ): RecyclerView.ViewHolder {
            val binding = LayoutGuestItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return GuestItemVH(binding, itemCheckboxCallback)
        }
    }
}