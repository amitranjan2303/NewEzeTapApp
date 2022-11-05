package com.amit.easytapapp.viewHolders

import com.amit.easytapapp.databinding.ItemLabelBinding
import com.amit.easytapapp.models.UIData

class ItemLabelViewHolder(val binding: ItemLabelBinding) : BaseViewHolder(binding.root) {
    override fun bind(item: UIData) {
        binding.datamodel = item
    }
}