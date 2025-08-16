package com.example.tedcard.presentation.campaigns.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tedcard.data.model.CampaignsItem
import com.example.tedcard.databinding.ItemCampaignBinding
import com.example.tedcard.util.downloadFromUrl
import com.example.tedcard.util.placeholderProgressBar

class CampaignAdapter(
    private val onClick: (CampaignsItem) -> Unit
) : ListAdapter<CampaignsItem, CampaignAdapter.CampaignViewHolder>(CampaignDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CampaignViewHolder {
        val binding = ItemCampaignBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CampaignViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CampaignViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CampaignViewHolder(private val binding: ItemCampaignBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CampaignsItem) {
            binding.textCampaignName.text = item.name

            val progressDrawable = placeholderProgressBar(binding.imageCampaign.context)
            binding.imageCampaign.downloadFromUrl(item.image, progressDrawable)

            binding.buttonDetail.setOnClickListener {
                onClick(item)
            }
        }
    }

    class CampaignDiffCallback : DiffUtil.ItemCallback<CampaignsItem>() {
        override fun areItemsTheSame(oldItem: CampaignsItem, newItem: CampaignsItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CampaignsItem, newItem: CampaignsItem) =
            oldItem == newItem
    }
}