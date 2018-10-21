package com.example.madiyar.kotlinggwp

import android.app.VoiceInteractor
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.madiyar.kotlinggwp.R
import kotlinx.android.synthetic.main.activity_main.*

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import java.util.ArrayList


class MainActivity : AppCompatActivity() {
    private val ArrayDescription = ArrayList<String>()
    private val ArrayTitles = ArrayList<String>()
    private val ArrayURLs = ArrayList<String>()
    private val ArrayImageURLs = ArrayList<String>()
    private val mInfoTextView: TextView? = null
    private val mErrorMsg: TextView? = null
    private var mAdapter: NewsListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myToolbar = findViewById<View>(R.id.my_toolbar) as Toolbar
        setSupportActionBar(myToolbar)
        val recyclerView = this.findViewById<View>(R.id.all_news_list_view) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = NewsListAdapter(this, ArrayTitles, ArrayDescription, ArrayURLs, ArrayImageURLs)
        recyclerView.adapter = mAdapter

        val queue = Volley.newRequestQueue(this)
        val Url = " https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fcybersport.com%2Ffeed"

        swipe_container.setOnRefreshListener {

        }

        val jsObjRequest2 =JsonObjectRequest(Request.Method.GET, Url, null, Response.Listener<JSONObject> {response ->
            val perevod = response.getJSONArray("items")
            for(i in 0 until perevod.length()){
                ArrayTitles.add(i, perevod.getJSONObject(i).getString("title"))
                ArrayDescription.add(i, perevod.getJSONObject(i).getString("description"))
                ArrayURLs.add(i, perevod.getJSONObject(i).getString("guid"))
                ArrayImageURLs.add(i, perevod.getJSONObject(i).getJSONObject("enclosure").getString("link"))
            }
            mAdapter!!.notifyDataSetChanged()
        }, Response.ErrorListener {

        })
        queue.add(jsObjRequest2)

    }


    /*@Override
    public void onRefresh() {
        Toast.makeText(this, R.string.refresh_started, Toast.LENGTH_SHORT).show();
        // начинаем показывать прогресс
        mSwipeRefreshLayout.setRefreshing(true);
        // ждем 3 секунды и прячем прогресс
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
                // говорим о том, что собираемся закончить
                Toast.makeText(MainActivity.this, R.string.refresh_finished, Toast.LENGTH_SHORT).show();
            }
        }, 3000);

    }*/


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.navigation, menu)//Menu Resource, Menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val mid = item.itemId
        if (mid == R.id.scores) {
            val intent = Intent(applicationContext, LiveGamesActivity::class.java)
            startActivity(intent)
        }
        return true
    }
}


