package com.app.baseadapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout.LayoutParams
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T : Any, VB : ViewBinding> : ListAdapter<T, ViewBindingVH<VB>>(BaseDiffUtil<T>()) {

    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewBindingVH<VB> {
        context = parent.context
        val view = inflateView(LayoutInflater.from(parent.context), viewType)
        return ViewBindingVH(view)
    }

    override fun onBindViewHolder(holder: ViewBindingVH<VB>, position: Int) {
        val item = getItem(position)
        bind(holder.binding, position, item = item)

        val layoutParam = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        holder.binding.root.layoutParams = layoutParam
        holder.binding.root.setOnClickListener {
            onItemClickListener(item = item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return itemType(position)
    }

    override fun getItemCount(): Int = currentList.size

    abstract fun inflateView(inflater: LayoutInflater, viewType: Int): VB

    abstract fun bind(binding: VB, position: Int, item: T)

    abstract fun itemType(position: Int): Int

    open fun onItemClickListener(item: T) {}
}

class BaseDiffUtil<T : Any> : DiffUtil.ItemCallback<T>() {

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}
