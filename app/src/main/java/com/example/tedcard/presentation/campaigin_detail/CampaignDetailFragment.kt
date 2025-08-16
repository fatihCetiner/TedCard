package com.example.tedcard.presentation.campaigin_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tedcard.data.model.CampaignsItem
import com.example.tedcard.databinding.FragmentCampaignDetailBinding
import com.example.tedcard.util.downloadFromUrl
import com.example.tedcard.util.placeholderProgressBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CampaignDetailFragment : Fragment() {

    private var _binding: FragmentCampaignDetailBinding? = null
    private val binding get() = _binding!!

    private val campaign: CampaignsItem?
        get() = arguments?.getParcelable("campaign")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCampaignDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        campaign?.let { item ->
            displayCampaignDetails(item)
        }
    }

    private fun displayCampaignDetails(item: CampaignsItem) {

        val progressDrawable = placeholderProgressBar(binding.imageCampaignDetail.context)
        binding.imageCampaignDetail.downloadFromUrl(item.image, progressDrawable)

        binding.textCampaignNameDetail.text = item.name
        binding.chipBadge.text = item.badge
        binding.textDiscountCategory.text = "${item.discountRate}% – ${item.benefitType}"

        val beginDate = item.beginOn.take(10)
        val endDate = item.endOn.take(10)
        binding.textDates.text = "$beginDate – $endDate"

        binding.textDescription.text = item.description.trim()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}