package com.example.footballleagueapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.footballleagueapi.R
import com.example.footballleagueapi.activity.DetailsLeagueActivity
import com.example.footballleagueapi.fragment.LastMatchFragment
import com.example.footballleagueapi.model.FootballLeagueData
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.items_row_league.*
import org.jetbrains.anko.startActivity

class FootballLeagueAdapter(private val context : Context, private val items : List<FootballLeagueData>) : RecyclerView.Adapter<FootballLeagueAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(context).inflate(R.layout.items_row_league, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    class ViewHolder(override val containerView : View) : RecyclerView.ViewHolder(containerView), LayoutContainer{
        fun bindItem(items : FootballLeagueData){
            tv_name.text = items.name

            Glide.with(itemView.context).load(items.image).apply(RequestOptions.overrideOf(250,250)).into(img_item_photo)

            itemView.setOnClickListener {
                itemView.context.startActivity<DetailsLeagueActivity>(
                    DetailsLeagueActivity.dataParcel to items,
                    LastMatchFragment.idLeague to items.id
                )
            }
        }
    }
}