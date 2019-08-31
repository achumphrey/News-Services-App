package com.example.newsservicesapp.presenter

import android.util.Log
import com.example.newsservicesapp.common.Constants
import com.example.newsservicesapp.common.enqueue
import com.example.newsservicesapp.network.ClientInterface
import com.example.newsservicesapp.network.RetrofitInstance

class PresenterImp (_view: ViewInterface?): PresenterInterface {

    var view: ViewInterface? = _view

    override fun processCall() {

        val clientInterface =
            RetrofitInstance().retrofitInstance.create(ClientInterface::class.java)

        val call = clientInterface.getNewsRecords(Constants.COUNTRY, Constants.API_KEY)

        // this is the extension function version of the callback enqueue

        call.enqueue {
            onResponse = {

                newsModel -> val newsModelRecords = newsModel.body()

                view?.onShowList(newsModelRecords!!)
            }
            onFailure = {
                    error -> Log.d("Fail", error?.message)
            }

        }//END CALL ENQUEUE
    }// END METHOD CALL

    override fun onDestroy() {
        view = null
    }

}