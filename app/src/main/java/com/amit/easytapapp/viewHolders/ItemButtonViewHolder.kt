package com.amit.easytapapp.viewHolders

import com.amit.easytapapp.callback.ItemActionCallBack
import com.amit.easytapapp.databinding.ItemButtonBinding
import com.amit.easytapapp.models.UIData

class ItemButtonViewHolder(val binding: ItemButtonBinding) : BaseViewHolder(binding.root) {

    private var itemActionCallBack: ItemActionCallBack? = null
    override fun bind(item: UIData) {
        binding.datamodel = item
        binding.btnSubmit.setOnClickListener({
            itemActionCallBack?.onItemClick(adapterPosition)
        })
    }

    fun setItemActionCallBack(itemActionCallBack: ItemActionCallBack?) {
        this.itemActionCallBack = itemActionCallBack
    }
}