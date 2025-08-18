package com.example.tedcard.presentation.campaigns.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.tedcard.data.model.CampaignsItem
import com.example.tedcard.databinding.ItemCampaignBinding
import com.example.tedcard.util.downloadFromUrl
import com.example.tedcard.util.placeholderProgressBar
import androidx.core.graphics.toColorInt
import com.example.tedcard.util.Constants.POPULAR
import com.example.tedcard.util.Constants.TUKENIYOR
import com.example.tedcard.util.Constants.YENI_FIRSAT

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

            val badgeColor = when (item.badge.lowercase()) {
                TUKENIYOR -> "#FFC107".toColorInt()
                POPULAR -> "#BA322E".toColorInt()
                YENI_FIRSAT -> "#4CAF50".toColorInt()
                else -> "#607D8B".toColorInt()
            }
            chipBadge.chipBackgroundColor = ColorStateList.valueOf(badgeColor)
        }

        fabDetail.setOnClickListener {
            onClick(item)
        }
    }
}