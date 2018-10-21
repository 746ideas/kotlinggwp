package com.example.madiyar.kotlinggwp
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.news_webview.*

class NewsWebview : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_webview)



        val url = intent.getStringExtra("url")
        webview_news.loadUrl(url)
    }
}
