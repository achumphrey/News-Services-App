package com.example.newsservicesapp.view.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsservicesapp.R
import com.example.newsservicesapp.common.inflate
import com.example.newsservicesapp.common.loadImage
import com.example.newsservicesapp.model.Articles
import com.example.newsservicesapp.model.NewsModel
import kotlinx.android.synthetic.main.fragment_recycler_view.view.*

class NewsAdapter (private val newsModel: NewsModel, private val listener: OnListClickLister):
    RecyclerView.Adapter<ListViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {

        val view: View = parent.inflate(R.layout.fragment_recycler_view, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  newsModel.articles.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        holder.tvTitle.text = newsModel.articles[position].title
        holder.tvDescription.text = newsModel.articles[position].description
        holder.imgView.loadImage(newsModel.articles[position].urlToImage)
        holder.bind(newsModel.articles[position], listener)
    }
}

class ListViewHolder (view: View): RecyclerView.ViewHolder(view){

    fun bind (articles: Articles, listener: OnListClickLister){
        itemView.setOnClickListener{
            listener.onListClick(articles)
        }
    }

    val tvTitle = view.tv_title
    val tvDescription = view.tv_description
    val imgView = view.iv_news_logo
}

interface OnListClickLister{

    fun onListClick(articles: Articles)
}