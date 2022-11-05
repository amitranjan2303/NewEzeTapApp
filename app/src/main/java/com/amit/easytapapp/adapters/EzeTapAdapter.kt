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
import com.amit.easytapapp.models.UIData
import com.amit.easytapapp.viewHolders.BaseViewHolder
import com.amit.easytapapp.viewHolders.ItemButtonViewHolder
import com.amit.easytapapp.viewHolders.ItemInputViewHolder
import com.amit.easytapapp.viewHolders.ItemLabelViewHolder
import java.util.*

class EzeTapAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private var itemList: ArrayList<UIData>? = ArrayList<UIData>()
    private var itemCallback: ItemActionCallBack? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        var viewHolder: BaseViewHolder? = null
        when (viewType) {
            R.layout.item_label -> {
                val viewBinding: ItemLabelBinding =
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        viewType,
                        parent,
                        false
                    )
                viewHolder = ItemLabelViewHolder(viewBinding)
            }
            R.layout.item_edit_text -> {
                val viewBinding: ItemEditTextBinding =
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        viewType,
                        parent,
                        false
                    )
                viewHolder = ItemInputViewHolder(viewBinding)
            }
            R.layout.item_button -> {
                val viewBinding: ItemButtonBinding =
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        viewType,
                        parent,
                        false
                    )
                viewHolder = ItemButtonViewHolder(viewBinding)
            }
        }
        return viewHolder!!
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val model: UIData? = itemList?.get(position)
        when (holder) {
            is ItemLabelViewHolder -> {
                model?.let {
                    holder.bind(model)
                }
            }
            is ItemInputViewHolder -> {
                model?.let { holder.bind(model)}
            }
            is ItemButtonViewHolder -> {
                model?.let {
                    holder.bind(model)
                    holder.setItemActionCallBack(itemCallback)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item: UIData? = itemList?.get(position)
        var viewType = -1
        item?.uiType?.let {
            when {
                it.equals("label") -> {
                    viewType = R.layout.item_label
                }
                it.equals("edittext") -> {
                    viewType = R.layout.item_edit_text
                }
                it.equals("button") -> {
                    viewType = R.layout.item_button
                }
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

    fun updateList(list: ArrayList<UIData>) {
        itemList?.clear()
        itemList = list
        notifyDataSetChanged()
    }

    fun getItemList(): ArrayList<UIData>? {
        return itemList
    }

    fun setItemCallBack(itemCallback: ItemActionCallBack?) {
        this.itemCallback = itemCallback
    }
}