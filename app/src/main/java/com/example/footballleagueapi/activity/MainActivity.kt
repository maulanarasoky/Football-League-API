package com.example.footballleagueapi.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballleagueapi.R
import com.example.footballleagueapi.adapter.FootballLeagueAdapter
import com.example.footballleagueapi.model.FootballLeagueData
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {

    private var items : MutableList<FootballLeagueData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initData()

        verticalLayout{
            padding = dip(16)
            lparams(width = matchParent, height = wrapContent)
            recyclerView {
                layoutManager = GridLayoutManager(context, 2)
                adapter = FootballLeagueAdapter(context, items)
            }.lparams(width = matchParent, height = matchParent)
        }
    }

    private fun initData(){
        val id = resources.getStringArray(R.array.football_id)
        val name = resources.getStringArray(R.array.football_name)
        val description = resources.getStringArray(R.array.football_description)

        val image = resources.obtainTypedArray(R.array.football_image)
        items.clear()

        for (i in name.indices){
            items.add(FootballLeagueData(id[i], name[i], description[i], image.getResourceId(i, 0)))
        }
        image.recycle()
    }
}
