package com.example.footballleagueapi.interfaces

import com.example.footballleagueapi.model.FootballLeagueMatch
import com.example.footballleagueapi.model.FootballTeamData

interface LeagueMatchView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(dataMatch : List<FootballLeagueMatch>)
}