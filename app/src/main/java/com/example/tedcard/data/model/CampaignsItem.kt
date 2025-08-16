package com.example.tedcard.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class CampaignsItem(
    val aktif: Int,
    val badge: String,
    val beginOn: String,
    val benefitType: String,
    val benefit_Id: Int,
    val campaign_Source: String,
    val codeActiveBeginOn: String,
    val codeActiveEndOn: String,
    val codeDisplayType: String,
    val description: String,
    val discountRate: Int,
    val endOn: String,
    val hashTag: String,
    val iL_ID: String,
    val id: Int,
    val image: String,
    val name: String,
    val oncelik: Int,
    val remainedCode: Int,
    val requestedCode: Int,
    val totalCode: Int,
    val usabilityFrequency: String,
    val usedCode: Int
): Parcelable