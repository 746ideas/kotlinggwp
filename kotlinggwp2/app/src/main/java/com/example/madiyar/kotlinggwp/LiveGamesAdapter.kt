package com.example.madiyar.kotlinggwp

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast


import com.squareup.picasso.Picasso

import java.util.ArrayList

import ImageTransformations.CircularTransformation
import kotlinx.android.synthetic.main.games_list_view.view.*

class LiveGamesAdapter(val mContext: Context, val info: ArrayList<LiveGamesActivity.TeamsAndScore>) : RecyclerView.Adapter<LiveGamesAdapter.LiveGamesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiveGamesAdapter.LiveGamesViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val view = inflater.inflate(R.layout.games_list_view, parent, false)

        return LiveGamesViewHolder(view)
    }

    override fun onBindViewHolder(holder: LiveGamesAdapter.LiveGamesViewHolder, position: Int) {
        if (info.size == 0) {
            Log.e("LiveGames", "Owibka bratan")
        }

        val textname1 = info[position].TeamName1
        val textname2 = info[position].TeamName2
        val textscore1 = info[position].Score1
        val textscore2 = info[position].Score2

        holder.itemView.team1_n.text = textname1
        holder.itemView.team2_n.text = textname2
        holder.itemView.team1_score.text = textscore1
        holder.itemView.team2_score.text = textscore2



        holder.itemView.score_holder.setOnClickListener {
            val intent = Intent(mContext, LiveMatchActivity::class.java)
            intent.putExtra("match_id", info[position].match_id.toString())
            mContext.startActivity(intent)
        }
    }

    fun clear() {
        info.clear()
        notifyDataSetChanged()
    }

    // Add a list of items -- change to type used
    fun addAll(list: ArrayList<LiveGamesActivity.TeamsAndScore>) {
        info.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return info.size
    }

    inner class LiveGamesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}