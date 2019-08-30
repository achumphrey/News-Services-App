package com.example.newsservicesapp.presenter

import com.example.newsservicesapp.model.NewsModel

interface ViewInterface {

    fun showProgress()
    fun showError()
    fun onShowList(newsModel: NewsModel)
}