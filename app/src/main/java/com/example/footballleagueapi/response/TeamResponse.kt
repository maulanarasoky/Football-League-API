package com.example.footballleagueapi.response

import com.example.footballleagueapi.model.FootballLeagueMatch
import com.example.footballleagueapi.model.FootballTeamData
import com.google.gson.annotations.SerializedName

data class TeamResponse(
    @SerializedName("teams")
    val teams: List<FootballTeamData>,

    @SerializedName("events")
    val match: List<FootballLeagueMatch>,

    @SerializedName("event")
    val allMatch : List<FootballLeagueMatch>
)