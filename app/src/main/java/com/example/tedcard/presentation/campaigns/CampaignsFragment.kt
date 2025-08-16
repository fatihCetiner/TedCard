package com.example.tedcard.presentation.campaigns

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tedcard.R
import com.example.tedcard.databinding.FragmentCampaignsBinding
import com.example.tedcard.presentation.campaigns.adapter.CampaignAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CampaignsFragment : Fragment() {

    private var _binding: FragmentCampaignsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CampaignViewModel by viewModels()
    private lateinit var adapter: CampaignAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCampaignsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        adapter = CampaignAdapter { campaign ->
            val bundle = Bundle().apply { putParcelable("campaign", campaign) }
            findNavController().navigate(
                R.id.campaignDetailFragment,
                bundle
            )
        }

        binding.recyclerViewCampaigns.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewCampaigns.adapter = adapter
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest { state ->
                    when(state) {
                        is CampaignUiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is CampaignUiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            adapter.submitList(state.data)
                        }
                        is CampaignUiState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}