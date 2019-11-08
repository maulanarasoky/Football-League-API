package com.example.footballleagueapi.presenter

import com.example.footballleagueapi.model.DetailsMatch
import com.google.gson.annotations.SerializedName

data class DetailsResponse (
    @SerializedName("events")
    val detailsMatch: List<DetailsMatch>
)