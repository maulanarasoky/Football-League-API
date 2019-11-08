package com.example.footballleagueapi.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.footballleagueapi.R
import com.example.footballleagueapi.adapter.FootballTeamAdapter
import com.example.footballleagueapi.adapter.ViewPagerAdapter
import com.example.footballleagueapi.api.ApiRepository
import com.example.footballleagueapi.fragment.LastMatchFragment
import com.example.footballleagueapi.fragment.NextMatchFragment
import com.example.footballleagueapi.interfaces.DetailsLeagueView
import com.example.footballleagueapi.model.FootballLeagueData
import com.example.footballleagueapi.model.FootballTeamData
import com.example.footballleagueapi.presenter.DetailsLeaguePresenter
import com.github.ybq.android.spinkit.style.Wave
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_details_league.*
import org.jetbrains.anko.startActivity

class DetailsLeagueActivity : AppCompatActivity(), DetailsLeagueView {

    companion object {
        const val dataParcel = "data_parcel"
    }

    private val lastMatchFragment : LastMatchFragment = LastMatchFragment()
    private val nextMatchFragment : NextMatchFragment = NextMatchFragment()

    private lateinit var presenter : DetailsLeaguePresenter

    private lateinit var progressBar : ProgressBar

    private lateinit var adapter : FootballTeamAdapter

    private var items : MutableList<FootballTeamData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_league)

        progressBar = findViewById(R.id.progressBar)

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.addFragment(lastMatchFragment, "Last Match")
        viewPagerAdapter.addFragment(nextMatchFragment, "Next Match")

        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)

        val styleProgressBar = Wave()
        progressBar.setIndeterminateDrawable(styleProgressBar)

        val dataLeague: FootballLeagueData? = intent.getParcelableExtra(dataParcel)

        supportActionBar?.title = dataLeague?.name

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Glide.with(this).load(dataLeague?.image).apply(RequestOptions.overrideOf(250, 250))
            .into(img_item_photo)
        leagueName.text = dataLeague?.name
        leagueDescription.text = dataLeague?.description

        adapter = FootballTeamAdapter(applicationContext, items)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()

        presenter = DetailsLeaguePresenter(this, request, gson)

        presenter.getTeamList(dataLeague?.name)

    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showTeamList(data: List<FootballTeamData>) {
        items.clear()
        items.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
