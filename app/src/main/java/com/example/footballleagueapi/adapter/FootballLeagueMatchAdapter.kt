package com.example.footballleagueapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.footballleagueapi.R
import com.example.footballleagueapi.activity.DetailsMatchActivity
import com.example.footballleagueapi.model.FootballLeagueMatch
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_row_match.*
import org.jetbrains.anko.startActivity

class FootballLeagueMatchAdapter(
    private val dataMatch: List<FootballLeagueMatch>
) : RecyclerView.Adapter<FootballLeagueMatchAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_row_match,
            parent,
            false
        )
    )

    override fun getItemCount() = dataMatch.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindItem(dataMatch[position])

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bindItem(dataMatch: FootballLeagueMatch) {
            matchName.text = dataMatch.eventName

            if(dataMatch.scoreHomeTeam != null && dataMatch.scoreAwayTeam != null){
                scoreHome.text = dataMatch.scoreHomeTeam.toString()
                scoreAway.text = dataMatch.scoreAwayTeam.toString()
            }else{
                scoreHome.text = "-"
                scoreAway.text = "-"
            }

            homeName.text = dataMatch.nameHomeTeam
            awayName.text = dataMatch.nameAwayTeam

            dateMatch.text = dataMatch.dateEvent


            itemView.setOnClickListener{
                itemView.context.startActivity<DetailsMatchActivity>(
                    DetailsMatchActivity.dataParcel to dataMatch
                )
            }
        }
    }
}