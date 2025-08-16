package com.example.tedcard.di

import com.example.tedcard.data.remote.CampaignApi
import com.example.tedcard.data.repository.CampaignRepositoryImpl
import com.example.tedcard.domain.repository.CampaignRepository
import com.example.tedcard.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideCampaignApi(retrofit: Retrofit): CampaignApi =
        retrofit.create(CampaignApi::class.java)

    @Provides
    @Singleton
    fun provideCampaignRepository(
        api: CampaignApi
    ): CampaignRepository = CampaignRepositoryImpl(api)
}