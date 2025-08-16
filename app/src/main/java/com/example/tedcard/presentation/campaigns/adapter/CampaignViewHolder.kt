package com.example.tedcard.presentation.campaigns.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.tedcard.data.model.CampaignsItem
import com.example.tedcard.databinding.ItemCampaignBinding
import com.example.tedcard.util.downloadFromUrl
import com.example.tedcard.util.placeholderProgressBar

class CampaignViewHolder(
    private val binding: ItemCampaignBinding,
    private val onClick: (CampaignsItem) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CampaignsItem) {
        binding.textCampaignName.text = item.name

        val progressDrawable = placeholderProgressBar(binding.imageCampaign.context)
        binding.imageCampaign.downloadFromUrl(item.image, progressDrawable)

        binding.buttonDetail.setOnClickListener {
            onClick(item)
        }
    }
}