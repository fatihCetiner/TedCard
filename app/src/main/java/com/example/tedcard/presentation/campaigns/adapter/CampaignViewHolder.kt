package com.example.tedcard.presentation.campaigns.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.tedcard.data.model.CampaignsItem
import com.example.tedcard.databinding.ItemCampaignBinding
import com.example.tedcard.util.downloadFromUrl
import com.example.tedcard.util.placeholderProgressBar

class CampaignViewHolder(
    private val binding: ItemCampaignBinding,
    private val onClick: (CampaignsItem) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CampaignsItem) = with(binding) {

        val progressDrawable = placeholderProgressBar(imageCampaign.context)
        imageCampaign.downloadFromUrl(item.image, progressDrawable)

        if (item.badge.isNullOrBlank()) {
            chipBadge.visibility = View.GONE
        } else {
            chipBadge.visibility = View.VISIBLE
            chipBadge.text = item.badge
        }

        fabDetail.setOnClickListener {
            onClick(item)
        }
    }
}