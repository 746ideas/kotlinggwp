package com.example.madiyar.kotlinggwp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.annotation.RequiresApi
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList
import android.support.v4.os.HandlerCompat.postDelayed



class LiveGamesActivity: AppCompatActivity() {
    private val info = ArrayList<TeamsAndScore>()
    private val mSwipeRefreshLayout: SwipeRefreshLayout? = null
    private var mLiveAdapter: LiveGamesAdapter? = null

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_livegames)

        val recyclerView = this.findViewById(R.id.all_games_list_view) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        val myToolbar = findViewById(R.id.my_toolbar) as Toolbar
        setSupportActionBar(myToolbar)
        mLiveAdapter = LiveGamesAdapter(this, info)
        recyclerView.adapter = mLiveAdapter
        val handler = Handler()
        val runnable = object : Runnable {
            override fun run() {
                mLiveAdapter!!.clear()
                fetchJson()
                handler.postDelayed(this, 20000)
            }
        }

//Start
        handler.postDelayed(runnable, 500)



    }


    fun fetchJson() {


        val queue = Volley.newRequestQueue(this)
        val matchUrl = "http://api.steampowered.com/IDOTA2Match_570/GetLiveLeagueGames/v1/?key=C2206A4558AD929913C2DFA15E982A79"

        val jsObjRequest3 = JsonObjectRequest(Request.Method.GET, matchUrl, null, Response.Listener { response ->
            var perevod: JSONArray? = null

            try {
                perevod = response.getJSONObject("result").getJSONArray("games")
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            for (i in 0 until perevod!!.length()) {
                try {
                    if (perevod.getJSONObject(i).has("scoreboard") && perevod.getJSONObject(i).getJSONObject("scoreboard").has("radiant") && perevod.getJSONObject(i).getJSONObject("scoreboard").has("dire") && perevod.getJSONObject(i).getJSONObject("scoreboard").getInt("duration") > 0) {
                        if (perevod.getJSONObject(i).has("radiant_team") && perevod.getJSONObject(i).has("dire_team")) {
                            info.add(TeamsAndScore(perevod.getJSONObject(i).getJSONObject("radiant_team").getString("team_name").toString(),
                                    perevod.getJSONObject(i).getJSONObject("dire_team").getString("team_name").toString(),
                                    perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("radiant").getString("score").toString(),
                                    perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("dire").getString("score").toString(),
                                    perevod.getJSONObject(i).getInt("match_id")))
                        } else if (perevod.getJSONObject(i).has("radiant_team") && !perevod.getJSONObject(i).has("dire_team")) {
                            info.add(TeamsAndScore(perevod.getJSONObject(i).getJSONObject("radiant_team").getString("team_name").toString(),
                                    "No Team Name",
                                    perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("radiant").getString("score").toString(),
                                    perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("dire").getString("score").toString(),
                                    perevod.getJSONObject(i).getInt("match_id")))
                        } else if (!perevod.getJSONObject(i).has("radiant_team") && perevod.getJSONObject(i).has("dire_team")) {
                            info.add(TeamsAndScore("No Team Name",
                                    perevod.getJSONObject(i).getJSONObject("dire_team").getString("team_name").toString(),
                                    perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("radiant").getString("score").toString(),
                                    perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("dire").getString("score").toString(),
                                    perevod.getJSONObject(i).getInt("match_id")))
                        } else {
                            info.add(TeamsAndScore("No Team Name",
                                    "No Team Name",
                                    perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("radiant").getString("score").toString(),
                                    perevod.getJSONObject(i).getJSONObject("scoreboard").getJSONObject("dire").getString("score").toString(),
                                    perevod.getJSONObject(i).getInt("match_id")))
                        }
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }

            mLiveAdapter!!.notifyDataSetChanged()
        }, Response.ErrorListener { })
        queue.add(jsObjRequest3)
    }


    class TeamsAndScore(internal var TeamName1: String,
                        internal var TeamName2: String,
                        internal var Score1: String,
                        internal var Score2: String,
                        internal var match_id: Int)


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu2, menu)//Menu Resource, Menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val mid = item.itemId
        if (mid == R.id.news) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
        return true
    }
}