package com.example.newsservicesapp.view.fragments


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.newsservicesapp.R
import com.example.newsservicesapp.model.Articles
import com.example.newsservicesapp.model.NewsModel
import com.example.newsservicesapp.presenter.PresenterImp
import com.example.newsservicesapp.presenter.ViewInterface
import com.example.newsservicesapp.view.adapter.NewsAdapter
import com.example.newsservicesapp.view.adapter.OnListClickLister
import kotlinx.android.synthetic.main.fragment_news_list.*

class NewsListFragment : Fragment(), ViewInterface {

    private lateinit var presenterImp: PresenterImp

    override fun showProgress() {
        prgs_bar.visibility = View.VISIBLE
    }

    override fun showError() {

    }

    override fun onShowList(newsModel: NewsModel) {

        prgs_bar.visibility = View.GONE

        val adapter: NewsAdapter =
            NewsAdapter(
                newsModel!!,
                object :
                    OnListClickLister {
                    override fun onListClick(articles: Articles) {

                        val url = articles.url
                        val builder = CustomTabsIntent.Builder()

                        val customTabsIntent: CustomTabsIntent = builder.build()
                        customTabsIntent.launchUrl(activity, Uri.parse(url))
                        }
                })

        rv_list.layoutManager = LinearLayoutManager(activity)
        rv_list.adapter = adapter

    }

    override fun onDestroy() { // this is the activity's onDestroy() method call
        super.onDestroy()
        presenterImp.onDestroy()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenterImp = PresenterImp(this)
        presenterImp.processCall()
        showProgress()




    }


}
