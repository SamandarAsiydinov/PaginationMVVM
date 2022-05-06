package com.example.paginationapp.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagedListAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paginationapp.R
import com.example.paginationapp.databinding.ItemLayoutBinding
import com.example.paginationapp.model.CharacterData

class RvAdapter: PagedListAdapter<CharacterData, RvAdapter.RvViewHolder>(DiffCallBack()) {

    private class DiffCallBack: DiffUtil.ItemCallback<CharacterData>() {
        override fun areItemsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        return RvViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class RvViewHolder(private val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CharacterData) {
            binding.apply {
                Glide.with(imageThumb)
                    .load(data.image)
                    .circleCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(imageThumb)

                tvTitle.text = data.name
                if (!TextUtils.isEmpty(data.species)) {
                    tvDesc.text = data.species
                } else {
                    tvDesc.text = "No description"
                }
            }
        }
    }
}