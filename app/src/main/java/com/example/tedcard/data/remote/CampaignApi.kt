package com.example.tedcard.data.remote

import com.example.tedcard.data.model.Campaigns
import retrofit2.http.GET

interface CampaignApi {
    @GET("Ted/GetKampanyalarLink?sehirId=0")
    suspend fun getCampaigns(): Campaigns
}