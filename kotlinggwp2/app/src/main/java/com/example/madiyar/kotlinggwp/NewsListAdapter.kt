package com.example.madiyar.kotlinggwp
import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.madiyar.kotlinggwp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_list_view.view.*
import java.util.ArrayList


class NewsListAdapter(val context: Context, val titles: ArrayList<String>, val descriptions: ArrayList<String>, val urls: ArrayList<String>, val imageUrls: ArrayList<String>): RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val view = inflater.inflate(R.layout.news_list_view, parent, false)

        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        Picasso.with(context).load(imageUrls.get(position)).into(holder.itemView.news_image)
        holder.itemView.news_title.setText(titles.get(position))
        holder.itemView.news_description.setText(descriptions.get(position))
        holder.itemView.card_news.setOnClickListener {
            val intent = Intent(context, NewsWebview::class.java)
            intent.putExtra("url", urls[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
