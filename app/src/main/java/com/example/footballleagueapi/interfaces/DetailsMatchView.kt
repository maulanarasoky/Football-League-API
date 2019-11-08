package com.example.footballleagueapi.interfaces

import com.example.footballleagueapi.model.DetailsMatch

interface DetailsMatchView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(detailsMatch : List<DetailsMatch>)
}