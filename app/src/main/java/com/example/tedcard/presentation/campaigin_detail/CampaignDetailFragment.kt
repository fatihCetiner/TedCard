package com.example.tedcard.presentation.campaigin_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.tedcard.R
import com.example.tedcard.data.model.CampaignsItem
import com.example.tedcard.databinding.FragmentCampaignDetailBinding
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

        campaign?.let {

            Toast.makeText(requireContext(), "Seçilen kampanya: ${it.name}", Toast.LENGTH_SHORT).show()
        } ?: run {
            Toast.makeText(requireContext(), "Kampanya bulunamadı!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}