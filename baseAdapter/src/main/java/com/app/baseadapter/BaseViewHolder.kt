package com.app.baseadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class ViewBindingVH<VB : ViewBinding> constructor(val binding: VB) : RecyclerView.ViewHolder(binding.root)
