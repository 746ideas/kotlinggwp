package com.example.madiyar.kotlinggwp
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import java.util.ArrayList
import java.util.HashMap

import ImageTransformations.CircularTransformation
import android.os.Handler
import kotlinx.android.synthetic.main.matchinfo.*

class LiveMatchActivity : AppCompatActivity() {
    private var GameID: MatchInfo? = null
    var HeroNameID = HashMap<Int, String>()
    var HeroLocalizedName = HashMap<Int, String>()
    var ItemNameID = HashMap<Int, String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.matchinfo)
        val handler = Handler()
        val runnable = object :Runnable {
            override fun run() {
                fetchJsonMatchInfo()
                handler.postDelayed(this, 20000)
            }
        }
        handler.postDelayed(runnable, 500)

    }



    fun fetchJsonMatchInfo(){
        val match_id = intent.extras!!.getString("match_id")
        val queue = Volley.newRequestQueue(this)
        val heroesImgUrl = "https://raw.githubusercontent.com/746ideas/ggwp/master/GGWP/heroes.json"
        val itemsImgUrl = "https://raw.githubusercontent.com/746ideas/ggwp/master/GGWP/items.json"
        val matchUrl = "http://api.steampowered.com/IDOTA2Match_570/GetLiveLeagueGames/v1/?key=C2206A4558AD929913C2DFA15E982A79"

        val TeamName1 = findViewById(R.id.match_info_team1_name) as TextView
        val TeamName2 = findViewById(R.id.match_info_team2_name) as TextView
        val Score1 = findViewById(R.id.match_info_score1) as TextView
        val Score2 = findViewById(R.id.match_info_score2) as TextView
        val playerName1 = findViewById(R.id.player1_name) as TextView
        val playerName2 = findViewById(R.id.player2_name) as TextView
        val playerName3 = findViewById(R.id.player3_name) as TextView
        val playerName4 = findViewById(R.id.player4_name) as TextView
        val playerName5 = findViewById(R.id.player5_name) as TextView
        val playerName6 = findViewById(R.id.player6_name) as TextView
        val playerName7 = findViewById(R.id.player7_name) as TextView
        val playerName8 = findViewById(R.id.player8_name) as TextView
        val playerName9 = findViewById(R.id.player9_name) as TextView
        val playerName10 = findViewById(R.id.player10_name) as TextView
        val playerScore1 = findViewById(R.id.player1_kda) as TextView
        val playerScore2 = findViewById(R.id.player2_kda) as TextView
        val playerScore3 = findViewById(R.id.player3_kda) as TextView
        val playerScore4 = findViewById(R.id.player4_kda) as TextView
        val playerScore5 = findViewById(R.id.player5_kda) as TextView
        val playerScore6 = findViewById(R.id.player6_kda) as TextView
        val playerScore7 = findViewById(R.id.player7_kda) as TextView
        val playerScore8 = findViewById(R.id.player8_kda) as TextView
        val playerScore9 = findViewById(R.id.player9_kda) as TextView
        val playerScore10 = findViewById(R.id.player10_kda) as TextView
        val player1Hero = findViewById(R.id.hero1_img) as ImageView
        val player2Hero = findViewById(R.id.hero2_img) as ImageView
        val player3Hero = findViewById(R.id.hero3_img) as ImageView
        val player4Hero = findViewById(R.id.hero4_img) as ImageView
        val player5Hero = findViewById(R.id.hero5_img) as ImageView
        val player6Hero = findViewById(R.id.hero6_img) as ImageView
        val player7Hero = findViewById(R.id.hero7_img) as ImageView
        val player8Hero = findViewById(R.id.hero8_img) as ImageView
        val player9Hero = findViewById(R.id.hero9_img) as ImageView
        val player10Hero = findViewById(R.id.hero10_img) as ImageView

        val player1item1 = findViewById(R.id.player1_item1) as ImageView
        val player1item2 = findViewById(R.id.player1_item2) as ImageView
        val player1item3 = findViewById(R.id.player1_item3) as ImageView
        val player1item4 = findViewById(R.id.player1_item4) as ImageView
        val player1item5 = findViewById(R.id.player1_item5) as ImageView
        val player1item6 = findViewById(R.id.player1_item6) as ImageView
        val player2item1 = findViewById(R.id.player2_item1) as ImageView
        val player2item2 = findViewById(R.id.player2_item2) as ImageView
        val player2item3 = findViewById(R.id.player2_item3) as ImageView
        val player2item4 = findViewById(R.id.player2_item4) as ImageView
        val player2item5 = findViewById(R.id.player2_item5) as ImageView
        val player2item6 = findViewById(R.id.player2_item6) as ImageView
        val player3item1 = findViewById(R.id.player3_item1) as ImageView
        val player3item2 = findViewById(R.id.player3_item2) as ImageView
        val player3item3 = findViewById(R.id.player3_item3) as ImageView
        val player3item4 = findViewById(R.id.player3_item4) as ImageView
        val player3item5 = findViewById(R.id.player3_item5) as ImageView
        val player3item6 = findViewById(R.id.player3_item6) as ImageView
        val player4item1 = findViewById(R.id.player4_item1) as ImageView
        val player4item2 = findViewById(R.id.player4_item2) as ImageView
        val player4item3 = findViewById(R.id.player4_item3) as ImageView
        val player4item4 = findViewById(R.id.player4_item4) as ImageView
        val player4item5 = findViewById(R.id.player4_item5) as ImageView
        val player4item6 = findViewById(R.id.player4_item6) as ImageView
        val player5item1 = findViewById(R.id.player5_item1) as ImageView
        val player5item2 = findViewById(R.id.player5_item2) as ImageView
        val player5item3 = findViewById(R.id.player5_item3) as ImageView
        val player5item4 = findViewById(R.id.player5_item4) as ImageView
        val player5item5 = findViewById(R.id.player5_item5) as ImageView
        val player5item6 = findViewById(R.id.player5_item6) as ImageView
        val player6item1 = findViewById(R.id.player6_item1) as ImageView
        val player6item2 = findViewById(R.id.player6_item2) as ImageView
        val player6item3 = findViewById(R.id.player6_item3) as ImageView
        val player6item4 = findViewById(R.id.player6_item4) as ImageView
        val player6item5 = findViewById(R.id.player6_item5) as ImageView
        val player6item6 = findViewById(R.id.player6_item6) as ImageView
        val player7item1 = findViewById(R.id.player7_item1) as ImageView
        val player7item2 = findViewById(R.id.player7_item2) as ImageView
        val player7item3 = findViewById(R.id.player7_item3) as ImageView
        val player7item4 = findViewById(R.id.player7_item4) as ImageView
        val player7item5 = findViewById(R.id.player7_item5) as ImageView
        val player7item6 = findViewById(R.id.player7_item6) as ImageView
        val player8item1 = findViewById(R.id.player8_item1) as ImageView
        val player8item2 = findViewById(R.id.player8_item2) as ImageView
        val player8item3 = findViewById(R.id.player8_item3) as ImageView
        val player8item4 = findViewById(R.id.player8_item4) as ImageView
        val player8item5 = findViewById(R.id.player8_item5) as ImageView
        val player8item6 = findViewById(R.id.player8_item6) as ImageView
        val player9item1 = findViewById(R.id.player9_item1) as ImageView
        val player9item2 = findViewById(R.id.player9_item2) as ImageView
        val player9item3 = findViewById(R.id.player9_item3) as ImageView
        val player9item4 = findViewById(R.id.player9_item4) as ImageView
        val player9item5 = findViewById(R.id.player9_item5) as ImageView
        val player9item6 = findViewById(R.id.player9_item6) as ImageView
        val player10item1 = findViewById(R.id.player10_item1) as ImageView
        val player10item2 = findViewById(R.id.player10_item2) as ImageView
        val player10item3 = findViewById(R.id.player10_item3) as ImageView
        val player10item4 = findViewById(R.id.player10_item4) as ImageView
        val player10item5 = findViewById(R.id.player10_item5) as ImageView
        val player10item6 = findViewById(R.id.player10_item6) as ImageView


        val hero1 = findViewById(R.id.player1_hero) as TextView
        val hero2 = findViewById(R.id.player2_hero) as TextView
        val hero3 = findViewById(R.id.player3_hero) as TextView
        val hero4 = findViewById(R.id.player4_hero) as TextView
        val hero5 = findViewById(R.id.player5_hero) as TextView
        val hero6 = findViewById(R.id.player6_hero) as TextView
        val hero7 = findViewById(R.id.player7_hero) as TextView
        val hero8 = findViewById(R.id.player8_hero) as TextView
        val hero9 = findViewById(R.id.player9_hero) as TextView
        val hero10 = findViewById(R.id.player10_hero) as TextView

        val networth1 = findViewById(R.id.player1_networth) as TextView
        val networth2 = findViewById(R.id.player2_networth) as TextView
        val networth3 = findViewById(R.id.player3_networth) as TextView
        val networth4 = findViewById(R.id.player4_networth) as TextView
        val networth5 = findViewById(R.id.player5_networth) as TextView
        val networth6 = findViewById(R.id.player6_networth) as TextView
        val networth7 = findViewById(R.id.player7_networth) as TextView
        val networth8 = findViewById(R.id.player8_networth) as TextView
        val networth9 = findViewById(R.id.player9_networth) as TextView
        val networth10 = findViewById(R.id.player10_networth) as TextView

        val level1 = findViewById(R.id.player1_lvl) as TextView
        val level2 = findViewById(R.id.player2_lvl) as TextView
        val level3 = findViewById(R.id.player3_lvl) as TextView
        val level4 = findViewById(R.id.player4_lvl) as TextView
        val level5 = findViewById(R.id.player5_lvl) as TextView
        val level6 = findViewById(R.id.player6_lvl) as TextView
        val level7 = findViewById(R.id.player7_lvl) as TextView
        val level8 = findViewById(R.id.player8_lvl) as TextView
        val level9 = findViewById(R.id.player9_lvl) as TextView
        val level10 = findViewById(R.id.player10_lvl) as TextView

        val gpmxpm1 = findViewById(R.id.player1_gpm) as TextView
        val gpmxpm2 = findViewById(R.id.player2_gpm) as TextView
        val gpmxpm3 = findViewById(R.id.player3_gpm) as TextView
        val gpmxpm4 = findViewById(R.id.player4_gpm) as TextView
        val gpmxpm5 = findViewById(R.id.player5_gpm) as TextView
        val gpmxpm6 = findViewById(R.id.player6_gpm) as TextView
        val gpmxpm7 = findViewById(R.id.player7_gpm) as TextView
        val gpmxpm8 = findViewById(R.id.player8_gpm) as TextView
        val gpmxpm9 = findViewById(R.id.player9_gpm) as TextView
        val gpmxpm10 = findViewById(R.id.player10_gpm) as TextView

        val goldradiant = findViewById<TextView>(R.id.radiant_gold)
        val golddire = findViewById<TextView>(R.id.dire_gold)

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        val jsObjRequest1 = JsonObjectRequest(Request.Method.GET, heroesImgUrl, null, Response.Listener { response ->
            try {
                val heroes = response.getJSONArray("heroes")
                for (i in 0 until heroes.length()) {
                    HeroNameID[heroes.getJSONObject(i).getInt("id")] = heroes.getJSONObject(i).getString("name").toString()
                    HeroLocalizedName[heroes.getJSONObject(i).getInt("id")] = heroes.getJSONObject(i).getString("localized_name").toString()
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }, Response.ErrorListener { })
        queue.add(jsObjRequest1)

        val jsObjRequest2 = JsonObjectRequest(Request.Method.GET, itemsImgUrl, null, Response.Listener { response ->
            try {
                val items = response.getJSONArray("items")
                for (i in 0 until items.length()) {
                    ItemNameID[items.getJSONObject(i).getInt("id")] = items.getJSONObject(i).getString("name")
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }, Response.ErrorListener { })
        queue.add(jsObjRequest2)

        val jsObjRequest = JsonObjectRequest(Request.Method.GET, matchUrl, null, Response.Listener { response ->
            var perevod: JSONArray? = null

            try {
                perevod = response.getJSONObject("result").getJSONArray("games")
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            for (i in 0 until perevod!!.length()) {
                val playerID = arrayOfNulls<String>(10)
                val playerName = arrayOfNulls<String>(10)
                val playerScore = arrayOfNulls<String>(10)
                val heroName = arrayOfNulls<String>(10)
                val heroLocalizedName = arrayOfNulls<String>(10)
                val networth = arrayOfNulls<String>(10)
                val level = arrayOfNulls<String>(10)
                val gpmxpm = arrayOfNulls<String>(10)
                val heroitems = Array(10) { arrayOfNulls<String>(6) }
                try {
                    if (perevod.getJSONObject(i).getInt("match_id").toString() == match_id) {

                        for (j in 0..9) {
                            if (j < 5) {
                                playerScore[j] = perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("radiant").getJSONArray("players").getJSONObject(j).getInt("kills").toString() + "/" +
                                        perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("radiant").getJSONArray("players").getJSONObject(j).getInt("death").toString() + "/" +
                                        perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("radiant").getJSONArray("players").getJSONObject(j).getInt("assists").toString()
                                playerID[j] = perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("radiant").getJSONArray("players").getJSONObject(j).getInt("account_id").toString()
                                heroName[j] = HeroNameID[perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("radiant").getJSONArray("players").getJSONObject(j).getInt("hero_id")]
                                heroLocalizedName[j] = HeroLocalizedName[perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("radiant").getJSONArray("players").getJSONObject(j).getInt("hero_id")]
                                networth[j] = perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("radiant").getJSONArray("players").getJSONObject(j).getInt("net_worth").toString()
                                level[j] = perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("radiant").getJSONArray("players").getJSONObject(j).getInt("level").toString()
                                gpmxpm[j] = perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("radiant").getJSONArray("players").getJSONObject(j).getInt("gold_per_min").toString() + "/" +
                                        perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("radiant").getJSONArray("players").getJSONObject(j).getInt("xp_per_min").toString()
                                heroitems[j][0] = ItemNameID[perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("radiant").getJSONArray("players").getJSONObject(j).getInt("item0")]
                                heroitems[j][1] = ItemNameID[perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("radiant").getJSONArray("players").getJSONObject(j).getInt("item1")]
                                heroitems[j][2] = ItemNameID[perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("radiant").getJSONArray("players").getJSONObject(j).getInt("item2")]
                                heroitems[j][3] = ItemNameID[perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("radiant").getJSONArray("players").getJSONObject(j).getInt("item3")]
                                heroitems[j][4] = ItemNameID[perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("radiant").getJSONArray("players").getJSONObject(j).getInt("item4")]
                                heroitems[j][5] = ItemNameID[perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("radiant").getJSONArray("players").getJSONObject(j).getInt("item5")]
                            } else {
                                playerScore[j] = perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("dire").getJSONArray("players").getJSONObject(j - 5).getInt("kills").toString() + "/" +
                                        perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("dire").getJSONArray("players").getJSONObject(j - 5).getInt("death").toString() + "/" +
                                        perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("dire").getJSONArray("players").getJSONObject(j - 5).getInt("assists").toString()
                                playerID[j] = perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("dire").getJSONArray("players").getJSONObject(j - 5).getInt("account_id").toString()
                                heroName[j] = HeroNameID[perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("dire").getJSONArray("players").getJSONObject(j - 5).getInt("hero_id")]
                                heroLocalizedName[j] = HeroLocalizedName[perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("dire").getJSONArray("players").getJSONObject(j - 5).getInt("hero_id")]
                                networth[j] = perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("dire").getJSONArray("players").getJSONObject(j - 5).getInt("net_worth").toString()
                                level[j] = perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("dire").getJSONArray("players").getJSONObject(j - 5).getInt("level").toString()
                                gpmxpm[j] = perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("dire").getJSONArray("players").getJSONObject(j - 5).getInt("gold_per_min").toString() + "/" +
                                        perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("dire").getJSONArray("players").getJSONObject(j - 5).getInt("xp_per_min").toString()
                                heroitems[j][0] = ItemNameID[perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("dire").getJSONArray("players").getJSONObject(j - 5).getInt("item0")]
                                heroitems[j][1] = ItemNameID[perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("dire").getJSONArray("players").getJSONObject(j - 5).getInt("item1")]
                                heroitems[j][2] = ItemNameID[perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("dire").getJSONArray("players").getJSONObject(j - 5).getInt("item2")]
                                heroitems[j][3] = ItemNameID[perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("dire").getJSONArray("players").getJSONObject(j - 5).getInt("item3")]
                                heroitems[j][4] = ItemNameID[perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("dire").getJSONArray("players").getJSONObject(j - 5).getInt("item4")]
                                heroitems[j][5] = ItemNameID[perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("dire").getJSONArray("players").getJSONObject(j - 5).getInt("item5")]
                            }
                        }

                        for (k in 0 until perevod.getJSONObject(i).getJSONArray("players").length()) {
                            for (j in 0..9) {
                                if (playerID[j] == perevod.getJSONObject(i).getJSONArray("players").getJSONObject(k).getInt("account_id").toString()) {
                                    playerName[j] = perevod.getJSONObject(i).getJSONArray("players").getJSONObject(k).getString("name").toString()
                                }
                            }
                        }
                        if (perevod.getJSONObject(i).has("radiant_team") && perevod.getJSONObject(i).has("dire_team")) {
                            GameID = MatchInfo(perevod.getJSONObject(i).getJSONObject("radiant_team")
                                    .getString("team_name").toString(), perevod.getJSONObject(i).getJSONObject("dire_team")
                                    .getString("team_name").toString(), perevod.getJSONObject(i).getJSONObject("scoreboard").
                                    getJSONObject("radiant").getString("score").toString(), perevod.getJSONObject(i).
                                    getJSONObject("scoreboard").getJSONObject("dire").getString("score").toString(),
                                    playerName[0], playerScore[0], playerName[1], playerScore[1], playerName[2], playerScore[2], playerName[3],
                                    playerScore[3], playerName[4], playerScore[4], playerName[5], playerScore[5], playerName[6], playerScore[6],
                                    playerName[7], playerScore[7], playerName[8], playerScore[8], playerName[9], playerScore[9], heroName[0],
                                    heroName[1], heroName[2], heroName[3], heroName[4], heroName[5], heroName[6], heroName[7], heroName[8],
                                    heroName[9], networth[0], networth[1], networth[2], networth[3], networth[4], networth[5], networth[6],
                                    networth[7], networth[8], networth[9], level[0], level[1], level[2], level[3], level[4], level[5], level[6],
                                    level[7], level[8], level[9], gpmxpm[0], gpmxpm[1], gpmxpm[2], gpmxpm[3], gpmxpm[4], gpmxpm[5], gpmxpm[6],
                                    gpmxpm[7], gpmxpm[8], gpmxpm[9], heroitems[0][0], heroitems[0][1], heroitems[0][2], heroitems[0][3],
                                    heroitems[0][4], heroitems[0][5], heroitems[1][0], heroitems[1][1], heroitems[1][2], heroitems[1][3],
                                    heroitems[1][4], heroitems[1][5], heroitems[2][0], heroitems[2][1], heroitems[2][2], heroitems[2][3],
                                    heroitems[2][4], heroitems[2][5], heroitems[3][0], heroitems[3][1], heroitems[3][2], heroitems[3][3],
                                    heroitems[3][4], heroitems[3][5], heroitems[4][0], heroitems[4][1], heroitems[4][2], heroitems[4][3],
                                    heroitems[4][4], heroitems[4][5], heroitems[5][0], heroitems[5][1], heroitems[5][2], heroitems[5][3],
                                    heroitems[5][4], heroitems[5][5], heroitems[6][0], heroitems[6][1], heroitems[6][2], heroitems[6][3],
                                    heroitems[6][4], heroitems[6][5], heroitems[7][0], heroitems[7][1], heroitems[7][2], heroitems[7][3],
                                    heroitems[7][4], heroitems[7][5], heroitems[8][0], heroitems[8][1], heroitems[8][2], heroitems[8][3],
                                    heroitems[8][4], heroitems[8][5], heroitems[9][0], heroitems[9][1], heroitems[9][2], heroitems[9][3],
                                    heroitems[9][4], heroitems[9][5], heroLocalizedName[0], heroLocalizedName[1], heroLocalizedName[2],
                                    heroLocalizedName[3], heroLocalizedName[4], heroLocalizedName[5], heroLocalizedName[6], heroLocalizedName[7],
                                    heroLocalizedName[8], heroLocalizedName[9])
                        } else if (perevod.getJSONObject(i).has("radiant_team") && !perevod.getJSONObject(i).has("dire_team")) {
                            GameID = MatchInfo(perevod.getJSONObject(i).getJSONObject("radiant_team")
                                    .getString("team_name").toString(), "No Team Name", perevod.getJSONObject(i).getJSONObject("scoreboard").
                                    getJSONObject("radiant").getString("score").toString(), perevod.getJSONObject(i).
                                    getJSONObject("scoreboard").getJSONObject("dire").getString("score").toString(),
                                    playerName[0], playerScore[0], playerName[1], playerScore[1], playerName[2], playerScore[2], playerName[3],
                                    playerScore[3], playerName[4], playerScore[4], playerName[5], playerScore[5], playerName[6], playerScore[6],
                                    playerName[7], playerScore[7], playerName[8], playerScore[8], playerName[9], playerScore[9], heroName[0],
                                    heroName[1], heroName[2], heroName[3], heroName[4], heroName[5], heroName[6], heroName[7], heroName[8],
                                    heroName[9], networth[0], networth[1], networth[2], networth[3], networth[4], networth[5], networth[6],
                                    networth[7], networth[8], networth[9], level[0], level[1], level[2], level[3], level[4], level[5], level[6],
                                    level[7], level[8], level[9], gpmxpm[0], gpmxpm[1], gpmxpm[2], gpmxpm[3], gpmxpm[4], gpmxpm[5], gpmxpm[6],
                                    gpmxpm[7], gpmxpm[8], gpmxpm[9], heroitems[0][0], heroitems[0][1], heroitems[0][2], heroitems[0][3],
                                    heroitems[0][4], heroitems[0][5], heroitems[1][0], heroitems[1][1], heroitems[1][2], heroitems[1][3],
                                    heroitems[1][4], heroitems[1][5], heroitems[2][0], heroitems[2][1], heroitems[2][2], heroitems[2][3],
                                    heroitems[2][4], heroitems[2][5], heroitems[3][0], heroitems[3][1], heroitems[3][2], heroitems[3][3],
                                    heroitems[3][4], heroitems[3][5], heroitems[4][0], heroitems[4][1], heroitems[4][2], heroitems[4][3],
                                    heroitems[4][4], heroitems[4][5], heroitems[5][0], heroitems[5][1], heroitems[5][2], heroitems[5][3],
                                    heroitems[5][4], heroitems[5][5], heroitems[6][0], heroitems[6][1], heroitems[6][2], heroitems[6][3],
                                    heroitems[6][4], heroitems[6][5], heroitems[7][0], heroitems[7][1], heroitems[7][2], heroitems[7][3],
                                    heroitems[7][4], heroitems[7][5], heroitems[8][0], heroitems[8][1], heroitems[8][2], heroitems[8][3],
                                    heroitems[8][4], heroitems[8][5], heroitems[9][0], heroitems[9][1], heroitems[9][2], heroitems[9][3],
                                    heroitems[9][4], heroitems[9][5], heroLocalizedName[0], heroLocalizedName[1], heroLocalizedName[2],
                                    heroLocalizedName[3], heroLocalizedName[4], heroLocalizedName[5], heroLocalizedName[6], heroLocalizedName[7],
                                    heroLocalizedName[8], heroLocalizedName[9])
                        } else if (!perevod.getJSONObject(i).has("radiant_team") && perevod.getJSONObject(i).has("dire_team")) {
                            GameID = MatchInfo("No Team Name", perevod.getJSONObject(i).getJSONObject("dire_team")
                                    .getString("team_name").toString(), perevod.getJSONObject(i).getJSONObject("scoreboard").
                                    getJSONObject("radiant").getString("score").toString(), perevod.getJSONObject(i).
                                    getJSONObject("scoreboard").getJSONObject("dire").getString("score").toString(),
                                    playerName[0], playerScore[0], playerName[1], playerScore[1], playerName[2], playerScore[2], playerName[3],
                                    playerScore[3], playerName[4], playerScore[4], playerName[5], playerScore[5], playerName[6], playerScore[6],
                                    playerName[7], playerScore[7], playerName[8], playerScore[8], playerName[9], playerScore[9], heroName[0],
                                    heroName[1], heroName[2], heroName[3], heroName[4], heroName[5], heroName[6], heroName[7], heroName[8],
                                    heroName[9], networth[0], networth[1], networth[2], networth[3], networth[4], networth[5], networth[6],
                                    networth[7], networth[8], networth[9], level[0], level[1], level[2], level[3], level[4], level[5], level[6],
                                    level[7], level[8], level[9], gpmxpm[0], gpmxpm[1], gpmxpm[2], gpmxpm[3], gpmxpm[4], gpmxpm[5], gpmxpm[6],
                                    gpmxpm[7], gpmxpm[8], gpmxpm[9], heroitems[0][0], heroitems[0][1], heroitems[0][2], heroitems[0][3],
                                    heroitems[0][4], heroitems[0][5], heroitems[1][0], heroitems[1][1], heroitems[1][2], heroitems[1][3],
                                    heroitems[1][4], heroitems[1][5], heroitems[2][0], heroitems[2][1], heroitems[2][2], heroitems[2][3],
                                    heroitems[2][4], heroitems[2][5], heroitems[3][0], heroitems[3][1], heroitems[3][2], heroitems[3][3],
                                    heroitems[3][4], heroitems[3][5], heroitems[4][0], heroitems[4][1], heroitems[4][2], heroitems[4][3],
                                    heroitems[4][4], heroitems[4][5], heroitems[5][0], heroitems[5][1], heroitems[5][2], heroitems[5][3],
                                    heroitems[5][4], heroitems[5][5], heroitems[6][0], heroitems[6][1], heroitems[6][2], heroitems[6][3],
                                    heroitems[6][4], heroitems[6][5], heroitems[7][0], heroitems[7][1], heroitems[7][2], heroitems[7][3],
                                    heroitems[7][4], heroitems[7][5], heroitems[8][0], heroitems[8][1], heroitems[8][2], heroitems[8][3],
                                    heroitems[8][4], heroitems[8][5], heroitems[9][0], heroitems[9][1], heroitems[9][2], heroitems[9][3],
                                    heroitems[9][4], heroitems[9][5], heroLocalizedName[0], heroLocalizedName[1], heroLocalizedName[2],
                                    heroLocalizedName[3], heroLocalizedName[4], heroLocalizedName[5], heroLocalizedName[6], heroLocalizedName[7],
                                    heroLocalizedName[8], heroLocalizedName[9])
                        } else {
                            GameID = MatchInfo("No Team Name", "No Team Name", perevod.getJSONObject(i).getJSONObject("scoreboard").
                                    getJSONObject("radiant").getString("score").toString(), perevod.getJSONObject(i).
                                    getJSONObject("scoreboard").getJSONObject("dire").getString("score").toString(),
                                    playerName[0], playerScore[0], playerName[1], playerScore[1], playerName[2], playerScore[2], playerName[3],
                                    playerScore[3], playerName[4], playerScore[4], playerName[5], playerScore[5], playerName[6], playerScore[6],
                                    playerName[7], playerScore[7], playerName[8], playerScore[8], playerName[9], playerScore[9], heroName[0],
                                    heroName[1], heroName[2], heroName[3], heroName[4], heroName[5], heroName[6], heroName[7], heroName[8],
                                    heroName[9], networth[0], networth[1], networth[2], networth[3], networth[4], networth[5], networth[6],
                                    networth[7], networth[8], networth[9], level[0], level[1], level[2], level[3], level[4], level[5], level[6],
                                    level[7], level[8], level[9], gpmxpm[0], gpmxpm[1], gpmxpm[2], gpmxpm[3], gpmxpm[4], gpmxpm[5], gpmxpm[6],
                                    gpmxpm[7], gpmxpm[8], gpmxpm[9], heroitems[0][0], heroitems[0][1], heroitems[0][2], heroitems[0][3],
                                    heroitems[0][4], heroitems[0][5], heroitems[1][0], heroitems[1][1], heroitems[1][2], heroitems[1][3],
                                    heroitems[1][4], heroitems[1][5], heroitems[2][0], heroitems[2][1], heroitems[2][2], heroitems[2][3],
                                    heroitems[2][4], heroitems[2][5], heroitems[3][0], heroitems[3][1], heroitems[3][2], heroitems[3][3],
                                    heroitems[3][4], heroitems[3][5], heroitems[4][0], heroitems[4][1], heroitems[4][2], heroitems[4][3],
                                    heroitems[4][4], heroitems[4][5], heroitems[5][0], heroitems[5][1], heroitems[5][2], heroitems[5][3],
                                    heroitems[5][4], heroitems[5][5], heroitems[6][0], heroitems[6][1], heroitems[6][2], heroitems[6][3],
                                    heroitems[6][4], heroitems[6][5], heroitems[7][0], heroitems[7][1], heroitems[7][2], heroitems[7][3],
                                    heroitems[7][4], heroitems[7][5], heroitems[8][0], heroitems[8][1], heroitems[8][2], heroitems[8][3],
                                    heroitems[8][4], heroitems[8][5], heroitems[9][0], heroitems[9][1], heroitems[9][2], heroitems[9][3],
                                    heroitems[9][4], heroitems[9][5], heroLocalizedName[0], heroLocalizedName[1], heroLocalizedName[2],
                                    heroLocalizedName[3], heroLocalizedName[4], heroLocalizedName[5], heroLocalizedName[6], heroLocalizedName[7],
                                    heroLocalizedName[8], heroLocalizedName[9])
                        }


                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }

        

            val textname1 = GameID!!.TeamName1
            val textname2 = GameID!!.TeamName2
            val textscore1 = GameID!!.Score1
            val textscore2 = GameID!!.Score2
            val player1Name = GameID!!.Player1Name
            val player2Name = GameID!!.Player2Name
            val player3Name = GameID!!.Player3Name
            val player4Name = GameID!!.Player4Name
            val player5Name = GameID!!.Player5Name
            val player6Name = GameID!!.Player6Name
            val player7Name = GameID!!.Player7Name
            val player8Name = GameID!!.Player8Name
            val player9Name = GameID!!.Player9Name
            val player10Name = GameID!!.Player10Name

            val player1Stats = GameID!!.Player1Stats
            val player2Stats = GameID!!.Player2Stats
            val player3Stats = GameID!!.Player3Stats
            val player4Stats = GameID!!.Player4Stats
            val player5Stats = GameID!!.Player5Stats
            val player6Stats = GameID!!.Player6Stats
            val player7Stats = GameID!!.Player7Stats
            val player8Stats = GameID!!.Player8Stats
            val player9Stats = GameID!!.Player9Stats
            val player10Stats = GameID!!.Player10Stats

            val mhero1 = GameID!!.Player1HeroName
            val mhero2 = GameID!!.Player2HeroName
            val mhero3 = GameID!!.Player3HeroName
            val mhero4 = GameID!!.Player4HeroName
            val mhero5 = GameID!!.Player5HeroName
            val mhero6 = GameID!!.Player6HeroName
            val mhero7 = GameID!!.Player7HeroName
            val mhero8 = GameID!!.Player8HeroName
            val mhero9 = GameID!!.Player9HeroName
            val mhero10 = GameID!!.Player10HeroName

            val urlhero1= GameID!!.Player1Hero
            val urlhero2= GameID!!.Player2Hero
            val urlhero3= GameID!!.Player3Hero
            val urlhero4= GameID!!.Player4Hero
            val urlhero5= GameID!!.Player5Hero
            val urlhero6= GameID!!.Player6Hero
            val urlhero7= GameID!!.Player7Hero
            val urlhero8= GameID!!.Player8Hero
            val urlhero9= GameID!!.Player9Hero
            val urlhero10= GameID!!.Player10Hero
            
            val hero1imageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + GameID!!.Player1Hero + "_vert.jpg"
            val hero2imageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + GameID!!.Player2Hero + "_vert.jpg"
            val hero3imageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + GameID!!.Player3Hero + "_vert.jpg"
            val hero4imageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + GameID!!.Player4Hero + "_vert.jpg"
            val hero5imageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + GameID!!.Player5Hero + "_vert.jpg"
            val hero6imageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + GameID!!.Player6Hero + "_vert.jpg"
            val hero7imageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + GameID!!.Player7Hero + "_vert.jpg"
            val hero8imageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + GameID!!.Player8Hero + "_vert.jpg"
            val hero9imageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + GameID!!.Player9Hero + "_vert.jpg"
            val hero10imageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + GameID!!.Player10Hero + "_vert.jpg"
            


            val mplayer1item1 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player1item1 + "_lg.png"
            val mplayer1item2 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player1item2 + "_lg.png"
            val mplayer1item3 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player1item3 + "_lg.png"
            val mplayer1item4 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player1item4 + "_lg.png"
            val mplayer1item5 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player1item5 + "_lg.png"
            val mplayer1item6 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player1item6 + "_lg.png"
            val mplayer2item1 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player2item1 + "_lg.png"
            val mplayer2item2 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player2item2 + "_lg.png"
            val mplayer2item3 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player2item3 + "_lg.png"
            val mplayer2item4 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player2item4 + "_lg.png"
            val mplayer2item5 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player2item5 + "_lg.png"
            val mplayer2item6 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player2item6 + "_lg.png"
            val mplayer3item1 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player3item1 + "_lg.png"
            val mplayer3item2 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player3item2 + "_lg.png"
            val mplayer3item3 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player3item3 + "_lg.png"
            val mplayer3item4 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player3item4 + "_lg.png"
            val mplayer3item5 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player3item5 + "_lg.png"
            val mplayer3item6 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player3item6 + "_lg.png"
            val mplayer4item1 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player4item1 + "_lg.png"
            val mplayer4item2 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player4item2 + "_lg.png"
            val mplayer4item3 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player4item3 + "_lg.png"
            val mplayer4item4 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player4item4 + "_lg.png"
            val mplayer4item5 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player4item5 + "_lg.png"
            val mplayer4item6 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player4item6 + "_lg.png"
            val mplayer5item1 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player5item1 + "_lg.png"
            val mplayer5item2 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player5item2 + "_lg.png"
            val mplayer5item3 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player5item3 + "_lg.png"
            val mplayer5item4 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player5item4 + "_lg.png"
            val mplayer5item5 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player5item5 + "_lg.png"
            val mplayer5item6 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player5item6 + "_lg.png"
            val mplayer6item1 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player6item1 + "_lg.png"
            val mplayer6item2 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player6item2 + "_lg.png"
            val mplayer6item3 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player6item3 + "_lg.png"
            val mplayer6item4 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player6item4 + "_lg.png"
            val mplayer6item5 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player6item5 + "_lg.png"
            val mplayer6item6 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player6item6 + "_lg.png"
            val mplayer7item1 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player7item1 + "_lg.png"
            val mplayer7item2 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player7item2 + "_lg.png"
            val mplayer7item3 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player7item3 + "_lg.png"
            val mplayer7item4 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player7item4 + "_lg.png"
            val mplayer7item5 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player7item5 + "_lg.png"
            val mplayer7item6 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player7item6 + "_lg.png"
            val mplayer8item1 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player8item1 + "_lg.png"
            val mplayer8item2 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player8item2 + "_lg.png"
            val mplayer8item3 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player8item3 + "_lg.png"
            val mplayer8item4 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player8item4 + "_lg.png"
            val mplayer8item5 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player8item5 + "_lg.png"
            val mplayer8item6 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player8item6 + "_lg.png"
            val mplayer9item1 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player9item1 + "_lg.png"
            val mplayer9item2 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player9item2 + "_lg.png"
            val mplayer9item3 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player9item3 + "_lg.png"
            val mplayer9item4 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player9item4 + "_lg.png"
            val mplayer9item5 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player9item5 + "_lg.png"
            val mplayer9item6 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player9item6 + "_lg.png"
            val mplayer10item1 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player10item1 + "_lg.png"
            val mplayer10item2 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player10item2 + "_lg.png"
            val mplayer10item3 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player10item3 + "_lg.png"
            val mplayer10item4 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player10item4 + "_lg.png"
            val mplayer10item5 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player10item5 + "_lg.png"
            val mplayer10item6 = "http://cdn.dota2.com/apps/dota2/images/items/" + GameID!!.player10item6 + "_lg.png"



            val mnetworth1 = GameID!!.networth1
            val mnetworth2 = GameID!!.networth2
            val mnetworth3 = GameID!!.networth3
            val mnetworth4 = GameID!!.networth4
            val mnetworth5 = GameID!!.networth5
            val mnetworth6 = GameID!!.networth6
            val mnetworth7 = GameID!!.networth7
            val mnetworth8 = GameID!!.networth8
            val mnetworth9 = GameID!!.networth9
            val mnetworth10 = GameID!!.networth10

            val mlevel1 = GameID!!.level1
            val mlevel2 = GameID!!.level2
            val mlevel3 = GameID!!.level3
            val mlevel4 = GameID!!.level4
            val mlevel5 = GameID!!.level5
            val mlevel6 = GameID!!.level6
            val mlevel7 = GameID!!.level7
            val mlevel8 = GameID!!.level8
            val mlevel9 = GameID!!.level9
            val mlevel10 = GameID!!.level10

            val mgpmxpm1 = GameID!!.gmpxpm1
            val mgpmxpm2 = GameID!!.gmpxpm2
            val mgpmxpm3 = GameID!!.gmpxpm3
            val mgpmxpm4 = GameID!!.gmpxpm4
            val mgpmxpm5 = GameID!!.gmpxpm5
            val mgpmxpm6 = GameID!!.gmpxpm6
            val mgpmxpm7 = GameID!!.gmpxpm7
            val mgpmxpm8 = GameID!!.gmpxpm8
            val mgpmxpm9 = GameID!!.gmpxpm9
            val mgpmxpm10 = GameID!!.gmpxpm10
            
            var LeyHo = HashMap<String, String>(10)

            LeyHo.put(mnetworth1!!, urlhero1!!)
            LeyHo.put(mnetworth2!!, urlhero2!!)
            LeyHo.put(mnetworth3!!, urlhero3!!)
            LeyHo.put(mnetworth4!!, urlhero4!!)
            LeyHo.put(mnetworth5!!, urlhero5!!)
            LeyHo.put(mnetworth6!!, urlhero6!!)
            LeyHo.put(mnetworth7!!, urlhero7!!)
            LeyHo.put(mnetworth8!!, urlhero8!!)
            LeyHo.put(mnetworth9!!, urlhero9!!)
            LeyHo.put(mnetworth10!!, urlhero10!!)

            var Heyho = IntArray(10)
            Heyho[0]= mnetworth1!!.toInt()
            Heyho[1]=mnetworth2!!.toInt()
            Heyho[2]=mnetworth3!!.toInt()
            Heyho[3]=mnetworth4!!.toInt()
            Heyho[4]=mnetworth5!!.toInt()
            Heyho[5]=mnetworth6!!.toInt()
            Heyho[6]=mnetworth7!!.toInt()
            Heyho[7]=mnetworth8!!.toInt()
            Heyho[8]=mnetworth9!!.toInt()
            Heyho[9]=mnetworth10!!.toInt()
            Heyho.sortDescending()

            var SeyHo = arrayOfNulls<String>(10)

            for(i in 0 until 10){
                SeyHo[i] = LeyHo.get(Heyho[i].toString())
            }
            hero1_bar_nw.setProgress(Heyho[0]*100/(Heyho[0]+Heyho[9]))
            hero2_bar_nw.setProgress(Heyho[1]*100/(Heyho[0]+Heyho[9]))
            hero3_bar_nw.setProgress(Heyho[2]*100/(Heyho[0]+Heyho[9]))
            hero4_bar_nw.setProgress(Heyho[3]*100/(Heyho[0]+Heyho[9]))
            hero5_bar_nw.setProgress(Heyho[4]*100/(Heyho[0]+Heyho[9]))
            hero6_bar_nw.setProgress(Heyho[5]*100/(Heyho[0]+Heyho[9]))
            hero7_bar_nw.setProgress(Heyho[6]*100/(Heyho[0]+Heyho[9]))
            hero8_bar_nw.setProgress(Heyho[7]*100/(Heyho[0]+Heyho[9]))
            hero9_bar_nw.setProgress(Heyho[8]*100/(Heyho[0]+Heyho[9]))
            hero10_bar_nw.setProgress(Heyho[9]*100/(Heyho[0]+Heyho[9]))
            if(SeyHo[0]!!.equals(urlhero1)|| SeyHo[0]!!.equals(urlhero2) || SeyHo[0]!!.equals(urlhero3) ||
                    SeyHo[0]!!.equals(urlhero4) || SeyHo[0]!!.equals(urlhero5)){
                hero1_bar_nw.progressDrawable = resources.getDrawable(R.drawable.radiant_nw)
            }else{
                hero1_bar_nw.progressDrawable = resources.getDrawable(R.drawable.dire_nw)
            }
            if(SeyHo[1]!!.equals(urlhero1)|| SeyHo[1]!!.equals(urlhero2) || SeyHo[1]!!.equals(urlhero3) ||
                    SeyHo[1]!!.equals(urlhero4) || SeyHo[1]!!.equals(urlhero5)){
                hero2_bar_nw.progressDrawable = resources.getDrawable(R.drawable.radiant_nw)
            }else{
                hero2_bar_nw.progressDrawable = resources.getDrawable(R.drawable.dire_nw)
            }
            if(SeyHo[2]!!.equals(urlhero1)|| SeyHo[2]!!.equals(urlhero2) || SeyHo[2]!!.equals(urlhero3) ||
                    SeyHo[2]!!.equals(urlhero4) || SeyHo[2]!!.equals(urlhero5)){
                hero3_bar_nw.progressDrawable = resources.getDrawable(R.drawable.radiant_nw)
            }else{
                hero3_bar_nw.progressDrawable = resources.getDrawable(R.drawable.dire_nw)
            }
            if(SeyHo[3]!!.equals(urlhero1)|| SeyHo[3]!!.equals(urlhero2) || SeyHo[3]!!.equals(urlhero3) ||
                    SeyHo[3]!!.equals(urlhero4) || SeyHo[3]!!.equals(urlhero5)){
                hero4_bar_nw.progressDrawable = resources.getDrawable(R.drawable.radiant_nw)
            }else{
                hero4_bar_nw.progressDrawable = resources.getDrawable(R.drawable.dire_nw)
            }
            if(SeyHo[4]!!.equals(urlhero1)|| SeyHo[4]!!.equals(urlhero2) || SeyHo[4]!!.equals(urlhero3) ||
                    SeyHo[4]!!.equals(urlhero4) || SeyHo[4]!!.equals(urlhero5)){
                hero5_bar_nw.progressDrawable = resources.getDrawable(R.drawable.radiant_nw)
            }else{
                hero5_bar_nw.progressDrawable = resources.getDrawable(R.drawable.dire_nw)
            }
            if(SeyHo[5]!!.equals(urlhero1)|| SeyHo[5]!!.equals(urlhero2) || SeyHo[5]!!.equals(urlhero3) ||
                    SeyHo[5]!!.equals(urlhero5) || SeyHo[5]!!.equals(urlhero5)){
                hero6_bar_nw.progressDrawable = resources.getDrawable(R.drawable.radiant_nw)
            }else{
                hero6_bar_nw.progressDrawable = resources.getDrawable(R.drawable.dire_nw)
            }
            if(SeyHo[6]!!.equals(urlhero1)|| SeyHo[6]!!.equals(urlhero2) || SeyHo[6]!!.equals(urlhero3) ||
                    SeyHo[6]!!.equals(urlhero4) || SeyHo[6]!!.equals(urlhero5)){
                hero7_bar_nw.progressDrawable = resources.getDrawable(R.drawable.radiant_nw)
            }else{
                hero7_bar_nw.progressDrawable = resources.getDrawable(R.drawable.dire_nw)
            }
            if(SeyHo[7]!!.equals(urlhero1)|| SeyHo[7]!!.equals(urlhero2) || SeyHo[7]!!.equals(urlhero3) ||
                    SeyHo[7]!!.equals(urlhero4) || SeyHo[7]!!.equals(urlhero5)){
                hero8_bar_nw.progressDrawable = resources.getDrawable(R.drawable.radiant_nw)
            }else{
                hero8_bar_nw.progressDrawable = resources.getDrawable(R.drawable.dire_nw)
            }
            if(SeyHo[8]!!.equals(urlhero1)|| SeyHo[8]!!.equals(urlhero2) || SeyHo[8]!!.equals(urlhero3) ||
                    SeyHo[8]!!.equals(urlhero4) || SeyHo[8]!!.equals(urlhero5)){
                hero9_bar_nw.progressDrawable = resources.getDrawable(R.drawable.radiant_nw)
            }else{
                hero9_bar_nw.progressDrawable = resources.getDrawable(R.drawable.dire_nw)
            }
            if(SeyHo[9]!!.equals(urlhero1)|| SeyHo[9]!!.equals(urlhero2) || SeyHo[9]!!.equals(urlhero3) ||
                    SeyHo[9]!!.equals(urlhero4) || SeyHo[9]!!.equals(urlhero5)){
                hero10_bar_nw.progressDrawable = resources.getDrawable(R.drawable.radiant_nw)
            }else{
                hero10_bar_nw.progressDrawable = resources.getDrawable(R.drawable.dire_nw)
            }
            hero1nw.setText(Heyho[0].toString())
            hero2nw.setText(Heyho[1].toString())
            hero3nw.setText(Heyho[2].toString())
            hero4nw.setText(Heyho[3].toString())
            hero5nw.setText(Heyho[4].toString())
            hero6nw.setText(Heyho[5].toString())
            hero7nw.setText(Heyho[6].toString())
            hero8nw.setText(Heyho[7].toString())
            hero9nw.setText(Heyho[8].toString())
            hero10nw.setText(Heyho[9].toString())
            val hero1netimageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + SeyHo[0] + "_sb.png"
            val hero2netimageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + SeyHo[1] + "_sb.png"
            val hero3netimageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + SeyHo[2] + "_sb.png"
            val hero4netimageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + SeyHo[3] + "_sb.png"
            val hero5netimageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + SeyHo[4] + "_sb.png"
            val hero6netimageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + SeyHo[5] + "_sb.png"
            val hero7netimageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + SeyHo[6] + "_sb.png"
            val hero8netimageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + SeyHo[7] + "_sb.png"
            val hero9netimageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + SeyHo[8] + "_sb.png"
            val hero10netimageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + SeyHo[9] + "_sb.png"

            TeamName1.text = textname1
            TeamName2.text = textname2
            Score1.text = textscore1
            Score2.text = textscore2

            playerName1.text = player1Name
            playerName2.text = player2Name
            playerName3.text = player3Name
            playerName4.text = player4Name
            playerName5.text = player5Name
            playerName6.text = player6Name
            playerName7.text = player7Name
            playerName8.text = player8Name
            playerName9.text = player9Name

            playerName10.text = player10Name
            playerScore1.text = player1Stats
            playerScore2.text = player2Stats
            playerScore3.text = player3Stats
            playerScore4.text = player4Stats
            playerScore5.text = player5Stats
            playerScore6.text = player6Stats
            playerScore7.text = player7Stats
            playerScore8.text = player8Stats
            playerScore9.text = player9Stats
            playerScore10.text = player10Stats

            hero1.text = mhero1
            hero2.text = mhero2
            hero3.text = mhero3
            hero4.text = mhero4
            hero5.text = mhero5
            hero6.text = mhero6
            hero7.text = mhero7
            hero8.text = mhero8
            hero9.text = mhero9
            hero10.text = mhero10

            networth1.text = mnetworth1
            networth2.text = mnetworth2
            networth3.text = mnetworth3
            networth4.text = mnetworth4
            networth5.text = mnetworth5
            networth6.text = mnetworth6
            networth7.text = mnetworth7
            networth8.text = mnetworth8
            networth9.text = mnetworth9
            networth10.text = mnetworth10

            level1.text = mlevel1
            level2.text = mlevel2
            level3.text = mlevel3
            level4.text = mlevel4
            level5.text = mlevel5
            level6.text = mlevel6
            level7.text = mlevel7
            level8.text = mlevel8
            level9.text = mlevel9
            level10.text = mlevel10

            gpmxpm1.text = mgpmxpm1
            gpmxpm2.text = mgpmxpm2
            gpmxpm3.text = mgpmxpm3
            gpmxpm4.text = mgpmxpm4
            gpmxpm5.text = mgpmxpm5
            gpmxpm6.text = mgpmxpm6
            gpmxpm7.text = mgpmxpm7
            gpmxpm8.text = mgpmxpm8
            gpmxpm9.text = mgpmxpm9
            gpmxpm10.text = mgpmxpm10

            Picasso.with(applicationContext).load(hero1netimageUrl).transform(CircularTransformation(94)).into(hero1_image_nw)
            Picasso.with(applicationContext).load(hero2netimageUrl).transform(CircularTransformation(94)).into(hero2_image_nw)
            Picasso.with(applicationContext).load(hero3netimageUrl).transform(CircularTransformation(94)).into(hero3_image_nw)
            Picasso.with(applicationContext).load(hero4netimageUrl).transform(CircularTransformation(94)).into(hero4_image_nw)
            Picasso.with(applicationContext).load(hero5netimageUrl).transform(CircularTransformation(94)).into(hero5_image_nw)
            Picasso.with(applicationContext).load(hero6netimageUrl).transform(CircularTransformation(94)).into(hero6_image_nw)
            Picasso.with(applicationContext).load(hero7netimageUrl).transform(CircularTransformation(94)).into(hero7_image_nw)
            Picasso.with(applicationContext).load(hero8netimageUrl).transform(CircularTransformation(94)).into(hero8_image_nw)
            Picasso.with(applicationContext).load(hero9netimageUrl).transform(CircularTransformation(94)).into(hero9_image_nw)
            Picasso.with(applicationContext).load(hero10netimageUrl).transform(CircularTransformation(94)).into(hero10_image_nw)
            
            
            Picasso.with(applicationContext).load(hero1imageUrl).transform(CircularTransformation(94)).into(player1Hero)
            Picasso.with(applicationContext).load(hero2imageUrl).transform(CircularTransformation(94)).into(player2Hero)
            Picasso.with(applicationContext).load(hero3imageUrl).transform(CircularTransformation(94)).into(player3Hero)
            Picasso.with(applicationContext).load(hero4imageUrl).transform(CircularTransformation(94)).into(player4Hero)
            Picasso.with(applicationContext).load(hero5imageUrl).transform(CircularTransformation(94)).into(player5Hero)
            Picasso.with(applicationContext).load(hero6imageUrl).transform(CircularTransformation(94)).into(player6Hero)
            Picasso.with(applicationContext).load(hero7imageUrl).transform(CircularTransformation(94)).into(player7Hero)
            Picasso.with(applicationContext).load(hero8imageUrl).transform(CircularTransformation(94)).into(player8Hero)
            Picasso.with(applicationContext).load(hero9imageUrl).transform(CircularTransformation(94)).into(player9Hero)
            Picasso.with(applicationContext).load(hero10imageUrl).transform(CircularTransformation(94)).into(player10Hero)

            Picasso.with(applicationContext).load(mplayer1item1).transform(CircularTransformation(94)).into(player1item1)
            Picasso.with(applicationContext).load(mplayer1item2).transform(CircularTransformation(94)).into(player1item2)
            Picasso.with(applicationContext).load(mplayer1item3).transform(CircularTransformation(94)).into(player1item3)
            Picasso.with(applicationContext).load(mplayer1item4).transform(CircularTransformation(94)).into(player1item4)
            Picasso.with(applicationContext).load(mplayer1item5).transform(CircularTransformation(94)).into(player1item5)
            Picasso.with(applicationContext).load(mplayer1item6).transform(CircularTransformation(94)).into(player1item6)
            Picasso.with(applicationContext).load(mplayer2item1).transform(CircularTransformation(94)).into(player2item1)
            Picasso.with(applicationContext).load(mplayer2item2).transform(CircularTransformation(94)).into(player2item2)
            Picasso.with(applicationContext).load(mplayer2item3).transform(CircularTransformation(94)).into(player2item3)
            Picasso.with(applicationContext).load(mplayer2item4).transform(CircularTransformation(94)).into(player2item4)
            Picasso.with(applicationContext).load(mplayer2item5).transform(CircularTransformation(94)).into(player2item5)
            Picasso.with(applicationContext).load(mplayer2item6).transform(CircularTransformation(94)).into(player2item6)
            Picasso.with(applicationContext).load(mplayer3item1).transform(CircularTransformation(94)).into(player3item1)
            Picasso.with(applicationContext).load(mplayer3item2).transform(CircularTransformation(94)).into(player3item2)
            Picasso.with(applicationContext).load(mplayer3item3).transform(CircularTransformation(94)).into(player3item3)
            Picasso.with(applicationContext).load(mplayer3item4).transform(CircularTransformation(94)).into(player3item4)
            Picasso.with(applicationContext).load(mplayer3item5).transform(CircularTransformation(94)).into(player3item5)
            Picasso.with(applicationContext).load(mplayer3item6).transform(CircularTransformation(94)).into(player3item6)
            Picasso.with(applicationContext).load(mplayer4item1).transform(CircularTransformation(94)).into(player4item1)
            Picasso.with(applicationContext).load(mplayer4item2).transform(CircularTransformation(94)).into(player4item2)
            Picasso.with(applicationContext).load(mplayer4item3).transform(CircularTransformation(94)).into(player4item3)
            Picasso.with(applicationContext).load(mplayer4item4).transform(CircularTransformation(94)).into(player4item4)
            Picasso.with(applicationContext).load(mplayer4item5).transform(CircularTransformation(94)).into(player4item5)
            Picasso.with(applicationContext).load(mplayer4item6).transform(CircularTransformation(94)).into(player4item6)
            Picasso.with(applicationContext).load(mplayer5item1).transform(CircularTransformation(94)).into(player5item1)
            Picasso.with(applicationContext).load(mplayer5item2).transform(CircularTransformation(94)).into(player5item2)
            Picasso.with(applicationContext).load(mplayer5item3).transform(CircularTransformation(94)).into(player5item3)
            Picasso.with(applicationContext).load(mplayer5item4).transform(CircularTransformation(94)).into(player5item4)
            Picasso.with(applicationContext).load(mplayer5item5).transform(CircularTransformation(94)).into(player5item5)
            Picasso.with(applicationContext).load(mplayer5item6).transform(CircularTransformation(94)).into(player5item6)
            Picasso.with(applicationContext).load(mplayer6item1).transform(CircularTransformation(94)).into(player6item1)
            Picasso.with(applicationContext).load(mplayer6item2).transform(CircularTransformation(94)).into(player6item2)
            Picasso.with(applicationContext).load(mplayer6item3).transform(CircularTransformation(94)).into(player6item3)
            Picasso.with(applicationContext).load(mplayer6item4).transform(CircularTransformation(94)).into(player6item4)
            Picasso.with(applicationContext).load(mplayer6item5).transform(CircularTransformation(94)).into(player6item5)
            Picasso.with(applicationContext).load(mplayer6item6).transform(CircularTransformation(94)).into(player6item6)
            Picasso.with(applicationContext).load(mplayer7item1).transform(CircularTransformation(94)).into(player7item1)
            Picasso.with(applicationContext).load(mplayer7item2).transform(CircularTransformation(94)).into(player7item2)
            Picasso.with(applicationContext).load(mplayer7item3).transform(CircularTransformation(94)).into(player7item3)
            Picasso.with(applicationContext).load(mplayer7item4).transform(CircularTransformation(94)).into(player7item4)
            Picasso.with(applicationContext).load(mplayer7item5).transform(CircularTransformation(94)).into(player7item5)
            Picasso.with(applicationContext).load(mplayer7item6).transform(CircularTransformation(94)).into(player7item6)
            Picasso.with(applicationContext).load(mplayer8item1).transform(CircularTransformation(94)).into(player8item1)
            Picasso.with(applicationContext).load(mplayer8item2).transform(CircularTransformation(94)).into(player8item2)
            Picasso.with(applicationContext).load(mplayer8item3).transform(CircularTransformation(94)).into(player8item3)
            Picasso.with(applicationContext).load(mplayer8item4).transform(CircularTransformation(94)).into(player8item4)
            Picasso.with(applicationContext).load(mplayer8item5).transform(CircularTransformation(94)).into(player8item5)
            Picasso.with(applicationContext).load(mplayer8item6).transform(CircularTransformation(94)).into(player8item6)
            Picasso.with(applicationContext).load(mplayer9item1).transform(CircularTransformation(94)).into(player9item1)
            Picasso.with(applicationContext).load(mplayer9item2).transform(CircularTransformation(94)).into(player9item2)
            Picasso.with(applicationContext).load(mplayer9item3).transform(CircularTransformation(94)).into(player9item3)
            Picasso.with(applicationContext).load(mplayer9item4).transform(CircularTransformation(94)).into(player9item4)
            Picasso.with(applicationContext).load(mplayer9item5).transform(CircularTransformation(94)).into(player9item5)
            Picasso.with(applicationContext).load(mplayer9item6).transform(CircularTransformation(94)).into(player9item6)
            Picasso.with(applicationContext).load(mplayer10item1).transform(CircularTransformation(94)).into(player10item1)
            Picasso.with(applicationContext).load(mplayer10item2).transform(CircularTransformation(94)).into(player10item2)
            Picasso.with(applicationContext).load(mplayer10item3).transform(CircularTransformation(94)).into(player10item3)
            Picasso.with(applicationContext).load(mplayer10item4).transform(CircularTransformation(94)).into(player10item4)
            Picasso.with(applicationContext).load(mplayer10item5).transform(CircularTransformation(94)).into(player10item5)
            Picasso.with(applicationContext).load(mplayer10item6).transform(CircularTransformation(94)).into(player10item6)


            val diregold = Integer.parseInt(mnetworth6) + Integer.parseInt(mnetworth7) + Integer.parseInt(mnetworth8) + Integer.parseInt(mnetworth9) + Integer.parseInt(mnetworth10)
            golddire.text = diregold.toString()
            val radiantgold = Integer.parseInt(mnetworth1) + Integer.parseInt(mnetworth2) + Integer.parseInt(mnetworth3) + Integer.parseInt(mnetworth4) + Integer.parseInt(mnetworth5)
            goldradiant.text = radiantgold.toString()

            progressBar.progress = radiantgold * 100 / (diregold + radiantgold)
            progressBar.progressDrawable = resources.getDrawable(R.drawable.progressbardr)



        }, Response.ErrorListener { })
        queue.add(jsObjRequest)
    }
    class MatchInfo( var TeamName1: String?,
                     var TeamName2: String?,
                     var Score1: String?,
                     var Score2: String?,
                     var Player1Name: String?,
                     var Player1Stats: String?,
                     var Player2Name: String?,
                     var Player2Stats: String?,
                     var Player3Name: String?,
                     var Player3Stats: String?,
                     var Player4Name: String?,
                     var Player4Stats: String?,
                     var Player5Name: String?,
                     var Player5Stats: String?,
                     var Player6Name: String?,
                     var Player6Stats: String?,
                     var Player7Name: String?,
                     var Player7Stats: String?,
                     var Player8Name: String?,
                     var Player8Stats: String?,
                     var Player9Name: String?,
                     var Player9Stats: String?,
                     var Player10Name: String?,
                     var Player10Stats: String?,
                     var Player1Hero: String?,
                     var Player2Hero: String?,
                     var Player3Hero: String?,
                     var Player4Hero: String?,
                     var Player5Hero: String?,
                     var Player6Hero: String?,
                     var Player7Hero: String?,
                     var Player8Hero: String?,
                     var Player9Hero: String?,
                     var Player10Hero: String?,
                     var networth1: String?,
                     var networth2: String?,
                     var networth3: String?,
                     var networth4: String?,
                     var networth5: String?,
                     var networth6: String?,
                     var networth7: String?,
                     var networth8: String?,
                     var networth9: String?,
                     var networth10: String?,
                     var level1: String?,
                     var level2: String?,
                     var level3: String?,
                     var level4: String?,
                     var level5: String?,
                     var level6: String?,
                     var level7: String?,
                     var level8: String?,
                     var level9: String?,
                     var level10: String?,
                     var gmpxpm1: String?,
                     var gmpxpm2: String?,
                     var gmpxpm3: String?,
                     var gmpxpm4: String?,
                     var gmpxpm5: String?,
                     var gmpxpm6: String?,
                     var gmpxpm7: String?,
                     var gmpxpm8: String?,
                     var gmpxpm9: String?,
                     var gmpxpm10: String?,
                     var player1item1: String?,
                     var player1item2: String?,
                     var player1item3: String?,
                     var player1item4: String?,
                     var player1item5: String?,
                     var player1item6: String?,
                     var player2item1: String?,
                     var player2item2: String?,
                     var player2item3: String?,
                     var player2item4: String?,
                     var player2item5: String?,
                     var player2item6: String?,
                     var player3item1: String?,
                     var player3item2: String?,
                     var player3item3: String?,
                     var player3item4: String?,
                     var player3item5: String?,
                     var player3item6: String?,
                     var player4item1: String?,
                     var player4item2: String?,
                     var player4item3: String?,
                     var player4item4: String?,
                     var player4item5: String?,
                     var player4item6: String?,
                     var player5item1: String?,
                     var player5item2: String?,
                     var player5item3: String?,
                     var player5item4: String?,
                     var player5item5: String?,
                     var player5item6: String?,
                     var player6item1: String?,
                     var player6item2: String?,
                     var player6item3: String?,
                     var player6item4: String?,
                     var player6item5: String?,
                     var player6item6: String?,
                     var player7item1: String?,
                     var player7item2: String?,
                     var player7item3: String?,
                     var player7item4: String?,
                     var player7item5: String?,
                     var player7item6: String?,
                     var player8item1: String?,
                     var player8item2: String?,
                     var player8item3: String?,
                     var player8item4: String?,
                     var player8item5: String?,
                     var player8item6: String?,
                     var player9item1: String?,
                     var player9item2: String?,
                     var player9item3: String?,
                     var player9item4: String?,
                     var player9item5: String?,
                     var player9item6: String?,
                     var player10item1: String?,
                     var player10item2: String?,
                     var player10item3: String?,
                     var player10item4: String?,
                     var player10item5: String?,
                     var player10item6: String?,
                     var Player1HeroName: String?,
                     var Player2HeroName: String?,
                     var Player3HeroName: String?,
                     var Player4HeroName: String?,
                     var Player5HeroName: String?,
                     var Player6HeroName: String?,
                     var Player7HeroName: String?,
                     var Player8HeroName: String?,
                     var Player9HeroName: String?,
                     var Player10HeroName: String?)

}
