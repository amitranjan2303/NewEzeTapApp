package com.amit.easytapapp.viewHolders

import com.amit.easytapapp.databinding.ItemEditTextBinding
import com.amit.easytapapp.models.UIData


class ItemInputViewHolder (val binding: ItemEditTextBinding) : BaseViewHolder(binding.root) {
    override fun bind(item: UIData) {
        binding.datamodel = item
    }
}