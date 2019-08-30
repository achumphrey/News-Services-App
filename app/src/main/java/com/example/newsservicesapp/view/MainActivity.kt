package com.example.newsservicesapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.example.newsservicesapp.R
import com.example.newsservicesapp.view.fragments.NewsListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment()
    }

    fun addFragment(){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.frm_container, NewsListFragment())
            //      .addToBackStack(null)
            .commit()
    }
}
