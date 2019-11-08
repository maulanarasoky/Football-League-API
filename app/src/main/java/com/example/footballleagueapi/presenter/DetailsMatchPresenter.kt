package com.example.footballleagueapi.presenter

import com.example.footballleagueapi.api.ApiRepository
import com.example.footballleagueapi.api.TheSportDBApi
import com.example.footballleagueapi.interfaces.DetailsMatchView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailsMatchPresenter (private val view : DetailsMatchView, private val apiRepository : ApiRepository, private val gson : Gson) {
    fun getDetailsMatch(idMatch : String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getDetailsMatch(idMatch)),
                DetailsResponse :: class.java)

            uiThread {
                view.hideLoading()
                view.showTeamList(data.detailsMatch)
            }
        }
    }
}