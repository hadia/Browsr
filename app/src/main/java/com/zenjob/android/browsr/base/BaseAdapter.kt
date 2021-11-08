package com.zenjob.android.browsr.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T : Any, Binding : ViewDataBinding>(
    diffCallback: DiffUtil.ItemCallback<T> = object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem == newItem

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem.equals(newItem)

        override fun getChangePayload(oldItem: T, newItem: T) = false
    }
) : ListAdapter<T, BaseViewHolder<Binding>>(diffCallback) {

    constructor(
        areItemsTheSame: (oldItem: T, newItem: T) -> Boolean =
            { oldItem, newItem -> oldItem == newItem },
        areContentsTheSame: (oldItem: T, newItem: T) -> Boolean =
            { oldItem, newItem -> oldItem == newItem },
        getChangePayload: (oldItem: T, newItem: T) -> Boolean = { _, _ -> false }
    ) : this(
        object : DiffUtil.ItemCallback<T>() {
            override fun areItemsTheSame(oldItem: T, newItem: T) = areItemsTheSame(oldItem, newItem)

            override fun areContentsTheSame(oldItem: T, newItem: T) =
                areContentsTheSame(oldItem, newItem)

            override fun getChangePayload(oldItem: T, newItem: T) =
                getChangePayload(oldItem, newItem)
        }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Binding> {
        val binding: Binding =
            inflate(LayoutInflater.from(parent.context), getLayoutResId(), parent, false)
        return BaseViewHolder(binding)
    }

    abstract fun getLayoutResId(): Int

    override fun onBindViewHolder(holder: BaseViewHolder<Binding>, position: Int) {
        bind(holder.binding, getItem(position), position)
    }

    abstract fun bind(binding: Binding, item: T, position: Int)
}

class BaseViewHolder<Binding : ViewDataBinding>(val binding: Binding) :
    RecyclerView.ViewHolder(binding.root)
