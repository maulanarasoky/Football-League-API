package com.example.footballleagueapi.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballleagueapi.R
import com.example.footballleagueapi.adapter.FootballLeagueMatchAdapter
import com.example.footballleagueapi.api.ApiRepository
import com.example.footballleagueapi.interfaces.LeagueMatchView
import com.example.footballleagueapi.model.FootballLeagueMatch
import com.example.footballleagueapi.presenter.ListMatchPresenter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_last_match.*
import org.jetbrains.anko.support.v4.find

/**
 * A simple [Fragment] subclass.
 */
class LastMatchFragment : Fragment(), LeagueMatchView {

    companion object{
        const val idLeague = "id_league"
    }

    private lateinit var progressBar : ProgressBar

    private lateinit var presenter : ListMatchPresenter

    private lateinit var adapter : FootballLeagueMatchAdapter

    private var items : MutableList<FootballLeagueMatch> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_last_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idLeague = activity!!.intent.getStringExtra(idLeague)

        progressBar = find(R.id.progressBar)

        val request = ApiRepository()
        val gson = Gson()

        presenter = ListMatchPresenter(this, request, gson)
        presenter.getLastMatchList(idLeague)

        adapter = FootballLeagueMatchAdapter(items)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }


    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun showTeamList(dataMatch: List<FootballLeagueMatch>) {
        items.clear()
        items.addAll(dataMatch)
        adapter.notifyDataSetChanged()
    }

}
