package com.example.footballleagueapi.presenter

import com.example.footballleagueapi.api.ApiRepository
import com.example.footballleagueapi.api.TheSportDBApi
import com.example.footballleagueapi.interfaces.DetailsLeagueView
import com.example.footballleagueapi.interfaces.LeagueMatchView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ListMatchPresenter(private val view : LeagueMatchView, private val apiRepository : ApiRepository, private val gson : Gson) {
    fun getLastMatchList(idLeague : String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getLastMatch(idLeague)),
                TeamResponse :: class.java)

            uiThread {
                view.hideLoading()
                view.showTeamList(data.match)
            }
        }
    }
}