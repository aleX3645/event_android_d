package com.alex3645.event_d.ui.feed

import android.os.Bundle
import android.telecom.Conference
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.alex3645.event_d.R
import com.alex3645.event_d.di.component.DaggerActivityComponent
import com.alex3645.event_d.di.module.ActivityModule
import com.alex3645.event_d.ui.eventsList.ConferenceListFragment
import javax.inject.Inject

class FeedActivity : AppCompatActivity(), FeedContract.View {

    @Inject
    lateinit var presenter: FeedContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        injectDependency()

        presenter.attach(this)
    }

    override fun onResume() {
        super.onResume()
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()

        activityComponent.inject(this)
    }

    override fun showListFragment() {
        supportFragmentManager.beginTransaction()
            .disallowAddToBackStack()
            .replace(R.id.frame, ConferenceListFragment().newInstance(), ConferenceListFragment.TAG)
            .commit()
    }

}