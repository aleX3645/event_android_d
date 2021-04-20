package com.alex3645.event_d.ui.feed

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.fragment.app.Fragment
import com.alex3645.event_d.R
import com.alex3645.event_d.di.component.DaggerActivityComponent
import com.alex3645.event_d.di.module.ActivityModule
import com.alex3645.event_d.ui.eventsList.ConferenceListFragment
import com.alex3645.event_d.ui.login.LoginFragment
import kotlinx.android.synthetic.main.activity_feed.*
import javax.inject.Inject


class FeedActivity : AppCompatActivity(), FeedContract.View {

    @Inject
    lateinit var presenter: FeedContract.Presenter

    var backFragment: Fragment = ConferenceListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_feed)
        injectDependency()

        presenter.attach(this)

        init()
    }

    private fun init(){
        topNavigationAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.auth -> {
                    showLoginFragment()
                    true
                }
                R.id.search -> {
                    // Handle search icon press
                    true
                }
                else -> false
            }
        }
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
        backFragment = ConferenceListFragment()
        supportFragmentManager.beginTransaction()
            .disallowAddToBackStack()
            .replace(R.id.frame, ConferenceListFragment().newInstance(), ConferenceListFragment.TAG)
            .commit()
    }

    override fun showLoginFragment() {
        backFragment = LoginFragment()
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.endter_from_right, R.anim.exit_to_left)
                .disallowAddToBackStack()
                .replace(R.id.frame, LoginFragment().newInstance(), LoginFragment.TAG)
                .commit()
    }

    override fun showSearchFragment() {
        TODO("Not yet implemented")
    }

}