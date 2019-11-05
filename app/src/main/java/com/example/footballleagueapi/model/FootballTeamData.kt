package com.example.footballleagueapi.model

import com.google.gson.annotations.SerializedName

data class FootballTeamData(
    @SerializedName("idTeam")
    var idTeam : Int? = 0,

    @SerializedName("strTeam")
    var teamName : String? = null,

    @SerializedName("strTeamBadge")
    var teamBadge : String? = null
)