package com.amit.easytapapp.viewHolders

import com.amit.easytapapp.databinding.ItemUserInfoBinding
import com.amit.easytapapp.models.UIData

class UserInfoViewHolder(val binding: ItemUserInfoBinding) : BaseViewHolder(binding.root) {

    override fun bind(item: UIData) {
        binding.datamodel = item

    }
}