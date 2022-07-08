package com.app.cicle

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.app.baseadapter.BaseAdapter
import com.app.cicle.databinding.NumberItemViewBinding
import com.app.cicle.databinding.TextItemViewBinding

class MainAdapter(val itemClick: (Item) -> Unit) : BaseAdapter<Item, ViewBinding>() {

    override fun inflateView(inflater: LayoutInflater, viewType: Int): ViewBinding {
        if (viewType == 0) {
            return TextItemViewBinding.inflate(inflater)
        } else {
            return NumberItemViewBinding.inflate(inflater)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun bind(binding: ViewBinding, position: Int, item: Item) {
        if (binding is TextItemViewBinding) {
            binding.itemTitle.text = "Text ${item.id}"
        } else if (binding is NumberItemViewBinding) {
            binding.itemTitle.text = "Number ${item.id}"
        }
    }

    override fun onItemClickListener(item: Item) {
        super.onItemClickListener(item)
        itemClick(item)
    }

    override fun itemType(position: Int): Int {
        return if (getItem(position).type == "Number") {
            0
        } else {
            1
        }
    }
}