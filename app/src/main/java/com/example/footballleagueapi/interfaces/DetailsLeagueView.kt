package com.example.footballleagueapi.interfaces

import com.example.footballleagueapi.model.FootballTeamData

interface DetailsLeagueView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data : List<FootballTeamData>)
}