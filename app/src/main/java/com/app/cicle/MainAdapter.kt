package com.app.cicle

import android.annotation.SuppressLint
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.app.baseadapter.BaseAdapter
import com.app.cicle.databinding.ComplexItemViewBinding
import com.app.cicle.databinding.NumberItemViewBinding
import com.app.cicle.databinding.TextItemViewBinding

class MainAdapter(val itemClick: (Item) -> Unit) : BaseAdapter<Item, ViewBinding>() {

    override fun inflateView(inflater: LayoutInflater, viewType: Int): ViewBinding {
        return when (viewType) {
            0 -> {
                TextItemViewBinding.inflate(inflater)
            }
            1 -> {
                ComplexItemViewBinding.inflate(inflater)
            }
            else -> {
                NumberItemViewBinding.inflate(inflater)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun bind(binding: ViewBinding, position: Int, item: Item) {
        if (binding is TextItemViewBinding) {
            binding.itemTitle.text = "Text ${item.id}"
        } else if (binding is NumberItemViewBinding) {
            binding.itemTitle.text = "Number ${item.id}"
        } else if (binding is ComplexItemViewBinding) {

        }
    }

    override fun onItemClickListener(item: Item) {
        super.onItemClickListener(item)
        itemClick(item)
    }

    override fun baseItemType(position: Int): Int {
        return when (getItem(position).type) {
            "Number" -> {
                0
            }
            "Complex" -> {
                1
            }
            else -> {
                2
            }
        }
    }
}