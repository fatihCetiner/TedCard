package com.example.tedcard.data.repository

import com.example.tedcard.data.model.Campaigns
import com.example.tedcard.data.remote.CampaignApi
import com.example.tedcard.domain.repository.CampaignRepository
import javax.inject.Inject

class CampaignRepositoryImpl @Inject constructor(
    private val api: CampaignApi
) : CampaignRepository {

    override suspend fun getCampaigns(): Campaigns {
        return api.getCampaigns()
    }
}