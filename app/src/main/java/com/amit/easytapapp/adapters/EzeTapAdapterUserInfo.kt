package com.amit.easytapapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.amit.easytapapp.R
import com.amit.easytapapp.callback.ItemActionCallBack
import com.amit.easytapapp.databinding.ItemButtonBinding
import com.amit.easytapapp.databinding.ItemEditTextBinding
import com.amit.easytapapp.databinding.ItemLabelBinding
import com.amit.easytapapp.databinding.ItemUserInfoBinding
import com.amit.easytapapp.models.UIData
import com.amit.easytapapp.viewHolders.*
import java.util.*

class EzeTapAdapterUserInfo : RecyclerView.Adapter<BaseViewHolder>() {

    private var itemList: ArrayList<UIData>? = ArrayList<UIData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        var viewHolder: BaseViewHolder? = null
        if (viewType == R.layout.item_label) {
            val viewBinding: ItemLabelBinding =
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    viewType,
                    parent,
                    false
                )
            viewHolder = ItemLabelViewHolder(viewBinding)
        } else if(viewType == R.layout.item_user_info){
            var viewBinding: ItemUserInfoBinding =
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    viewType,
                    parent,
                    false
                )
            viewHolder = UserInfoViewHolder(viewBinding)
        }

        return viewHolder!!
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val model: UIData? = itemList?.get(position)
        if (holder is ItemLabelViewHolder) {
            model?.let {
                (holder as ItemLabelViewHolder).bind(model)
            }
        } else if (holder is UserInfoViewHolder) {
            model?.let {
                (holder as UserInfoViewHolder).bind(model)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        var item: UIData? = itemList?.get(position)
        var viewType = -1;
        item?.uitype?.let {
            if (it.equals("label")) {
                viewType = R.layout.item_label
            } else {
                viewType = R.layout.item_user_info
            }
        }
        return viewType
    }

    override fun getItemCount(): Int {
        var count = 0
        itemList?.let {
            count = itemList?.size!!
        }
        return count
    }

    fun updateList(list: ArrayList<UIData>?) {
        itemList?.clear()
        itemList = list
        notifyDataSetChanged()
    }

}