package com.example.footballleagueapi.activity

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.footballleagueapi.R
import com.example.footballleagueapi.adapter.FootballLeagueMatchAdapter
import com.example.footballleagueapi.api.ApiRepository
import com.example.footballleagueapi.interfaces.LeagueMatchView
import com.example.footballleagueapi.model.FootballLeagueMatch
import com.example.footballleagueapi.presenter.ListMatchPresenter
import com.google.gson.Gson
import org.jetbrains.anko.find

class SearchMatchActivity : AppCompatActivity(), LeagueMatchView {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: ListMatchPresenter
    private lateinit var adapter: FootballLeagueMatchAdapter
    private val dataMatch: MutableList<FootballLeagueMatch> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)

        supportActionBar?.title = "Search for Match"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        progressBar = find(R.id.progressBar)

        adapter = FootballLeagueMatchAdapter(dataMatch)

        recyclerView = find(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.menu, menu)

        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("", false)
                searchItem.collapseActionView()


                val request = ApiRepository()
                val gson = Gson()

                presenter = ListMatchPresenter(this@SearchMatchActivity, request, gson)

                presenter.searchMatch(query)

                recyclerView.adapter = adapter

                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
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
        progressBar.visibility = View.INVISIBLE
    }

    override fun showTeamList(dataMatch: List<FootballLeagueMatch>) {
        this.dataMatch.clear()
        this.dataMatch.addAll(dataMatch)
        adapter.notifyDataSetChanged()
    }
}
