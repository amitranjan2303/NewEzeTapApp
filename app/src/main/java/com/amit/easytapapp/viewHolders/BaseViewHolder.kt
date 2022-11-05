package com.amit.easytapapp.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.amit.easytapapp.models.UIData

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: UIData)
}