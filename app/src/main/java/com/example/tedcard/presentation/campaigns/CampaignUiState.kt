package com.example.tedcard.presentation.campaigns

import com.example.tedcard.data.model.Campaigns

sealed class CampaignUiState {
    object Loading : CampaignUiState()
    data class Success(val data: Campaigns) : CampaignUiState()
    data class Error(val message: String) : CampaignUiState()
}