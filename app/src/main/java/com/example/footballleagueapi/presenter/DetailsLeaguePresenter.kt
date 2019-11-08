package com.example.footballleagueapi.presenter

import com.example.footballleagueapi.api.ApiRepository
import com.example.footballleagueapi.api.TheSportDBApi
import com.example.footballleagueapi.interfaces.DetailsLeagueView
import com.example.footballleagueapi.response.TeamResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailsLeaguePresenter(private val view : DetailsLeagueView, private val apiRepository : ApiRepository, private val gson : Gson){
    fun getTeamList(league : String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeams(league)),
                TeamResponse:: class.java)

            uiThread {
                view.hideLoading()
                view.showTeamList(data.teams)
            }
        }
    }
}