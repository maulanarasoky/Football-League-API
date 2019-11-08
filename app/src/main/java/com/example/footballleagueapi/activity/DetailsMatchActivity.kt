package com.example.footballleagueapi.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.footballleagueapi.R
import com.example.footballleagueapi.api.ApiRepository
import com.example.footballleagueapi.interfaces.DetailsMatchView
import com.example.footballleagueapi.model.DetailsMatch
import com.example.footballleagueapi.model.FootballLeagueMatch
import com.example.footballleagueapi.presenter.DetailsMatchPresenter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_details_match.*
import org.jetbrains.anko.find

class DetailsMatchActivity : AppCompatActivity(), DetailsMatchView {

    companion object{
        const val dataParcel = "data_parcel"
    }

    private var dataDetailsMatch : MutableList<DetailsMatch> = mutableListOf()

    private lateinit var match : DetailsMatch

    private lateinit var progressBar : ProgressBar
    private lateinit var presenter : DetailsMatchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_match)

        val dataMatch : FootballLeagueMatch? = intent.getParcelableExtra(dataParcel)

        supportActionBar?.title = dataMatch?.eventName

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        progressBar = find(R.id.progressBar)


        val request = ApiRepository()
        val gson = Gson()

        presenter = DetailsMatchPresenter(this, request, gson)

        presenter.getDetailsMatch(dataMatch?.idEvent)

        if(dataMatch?.strThumb != null){
            Glide.with(this).load(dataMatch.strThumb).apply(RequestOptions.overrideOf(500,500)).into(imgHeader)

        }else{
            Glide.with(this).load(R.drawable.ball_header_black).apply(RequestOptions.overrideOf(500,500)).into(imgHeader)

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showTeamList(detailsMatch: List<DetailsMatch>) {
        dataDetailsMatch.clear()
        dataDetailsMatch.addAll(detailsMatch)
        match = DetailsMatch(
            dataDetailsMatch[0].eventName,
            dataDetailsMatch[0].dateEvent,
            dataDetailsMatch[0].homeTeamName,
            dataDetailsMatch[0].awayTeamName,
            dataDetailsMatch[0].homeScore,
            dataDetailsMatch[0].awayScore,
            dataDetailsMatch[0].homeFormation,
            dataDetailsMatch[0].awayFormation
        )
        matchName.text = match.eventName
        dateMatch.text = match.dateEvent

        homeName.text = match.homeTeamName
        awayName.text = match.awayTeamName
        if(match.homeScore != null && match.awayScore != null){
            scoreHome.text = match.homeScore.toString()
            scoreAway.text = match.awayScore.toString()
        }else{
            scoreHome.text = "0"
            scoreAway.text = "0"
        }
        if(match.homeFormation == null){
            homeFormation.text = "-"
        }else{
            homeFormation.text = match.homeFormation
        }
        if(match.awayFormation == null){
            awayFormation.text = "-"
        }else{
            awayFormation.text = match.awayFormation
        }
        imgHeader.visibility = View.VISIBLE

        textTeamName.visibility = View.VISIBLE
        textScore.visibility = View.VISIBLE
        textFormation.visibility = View.VISIBLE
    }
}
