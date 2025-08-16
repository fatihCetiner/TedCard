package com.example.tedcard.presentation.campaigns

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tedcard.domain.repository.CampaignRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CampaignViewModel @Inject constructor(
    private val repository: CampaignRepository
) : ViewModel() {

    private val _state = MutableStateFlow<CampaignUiState>(CampaignUiState.Loading)
    val state: StateFlow<CampaignUiState> = _state

    init {
        fetchCampaigns()
    }

    private fun fetchCampaigns() {
        viewModelScope.launch {
            try {
                val result = repository.getCampaigns()
                _state.value = CampaignUiState.Success(result)
            } catch (e: Exception) {
                _state.value = CampaignUiState.Error(e.localizedMessage ?: "Error")
            }
        }
    }
}