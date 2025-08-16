package com.example.tedcard.domain.repository

import com.example.tedcard.data.model.Campaigns

interface CampaignRepository {
    suspend fun getCampaigns(): Campaigns
}