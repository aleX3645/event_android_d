package com.alex3645.event_d.ui.feed

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.alex3645.event_d.R
import com.alex3645.event_d.di.component.DaggerActivityComponent
import com.alex3645.event_d.di.module.ActivityModule
import com.alex3645.event_d.ui.eventsList.ConferenceListFragment
import com.alex3645.event_d.ui.login.LoginFragment
import com.alex3645.event_d.ui.search.SearchFragmentList
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_feed.*
import java.util.*
import javax.inject.Inject


class FeedActivity : AppCompatActivity(), FeedContract.View {

    @Inject
    lateinit var presenter: FeedContract.Presenter

    var stack = Stack<Fragment>()

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
                    showSearchFragment()
                    true
                }
                else -> false
            }
        }

        bottom_navigation.setOnNavigationItemSelectedListener(object: BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.page_1 -> {
                        showListFragment()
                        true
                    }
                    else -> false
                }
                return true
            }

        })
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

    override fun onBackPressed() {
        if(stack.size <= 1){
            super.onBackPressed()
            return
        }

        var oldTag = stack.peek().tag.toString()
        stack.pop()
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(considerEnterAnimation(oldTag, ConferenceListFragment.TAG),considerExitAnimation(oldTag, ConferenceListFragment.TAG))
                .disallowAddToBackStack()
                .replace(R.id.frame, stack.peek(), stack.peek().tag)
                .commit()
    }


    override fun showListFragment() {
        var oldTag = ConferenceListFragment.TAG
        if(stack.size >= 2){
            oldTag = stack.peek().tag.toString()
            stack.pop()
            stack.pop()
        }

        if(stack.size == 1){
            return
        }

        val fragment = ConferenceListFragment().newInstance()
        stack.push(fragment)

        supportFragmentManager.beginTransaction()
                .setCustomAnimations(considerEnterAnimation(oldTag, ConferenceListFragment.TAG),considerExitAnimation(oldTag, ConferenceListFragment.TAG))
            .disallowAddToBackStack()
            .replace(R.id.frame, fragment, ConferenceListFragment.TAG)
            .commit()
    }

    override fun showLoginFragment() {
        if(stack.isNullOrEmpty() || stack.peek().tag == LoginFragment.TAG){
            return
        }

        var oldTag = ConferenceListFragment.TAG.toString()
        if(stack.size >= 2){
            oldTag = stack.peek().tag.toString()
            stack.pop()
        }

        val fragment = LoginFragment().newInstance()
        stack.push(fragment)

        supportFragmentManager.beginTransaction()
                .setCustomAnimations(considerEnterAnimation(oldTag, LoginFragment.TAG),considerExitAnimation(oldTag, LoginFragment.TAG))
                .disallowAddToBackStack()
                .replace(R.id.frame, fragment, LoginFragment.TAG)
                .commit()
    }

    override fun showSearchFragment() {
        if(stack.isNullOrEmpty() || stack.peek().tag == SearchFragmentList.TAG){
            return
        }

        var oldTag = ConferenceListFragment.TAG.toString()
        if(stack.size >= 2){
            oldTag = stack.peek().tag.toString()
            stack.pop()
        }

        val fragment = SearchFragmentList().newInstance()
        stack.push(fragment)

        supportFragmentManager.beginTransaction()
                .setCustomAnimations(considerEnterAnimation(oldTag, SearchFragmentList.TAG), considerExitAnimation(oldTag, SearchFragmentList.TAG))
                .disallowAddToBackStack()
                .replace(R.id.frame, fragment, SearchFragmentList.TAG)
                .commit()
    }

    private fun considerEnterAnimation(curTag: String, newTag: String): Int{
        Log.d("anim", curTag+ " nTag: " + newTag)

        if(newTag == SearchFragmentList.TAG){
            return R.anim.enter_from_up
        }

        if(newTag == LoginFragment.TAG){
            return R.anim.enter_from_right
        }

        if(newTag == ConferenceListFragment.TAG && curTag == SearchFragmentList.TAG){
            return R.anim.enter_from_bottom
        }

        if(newTag == ConferenceListFragment.TAG && curTag == LoginFragment.TAG){
            return R.anim.enter_from_left
        }

        return R.anim.enter_from_up
    }

    private fun considerExitAnimation(curTag: String, newTag: String): Int{
        if(newTag == SearchFragmentList.TAG){
            return R.anim.exit_to_bottom
        }

        if(newTag == LoginFragment.TAG){
            return R.anim.exit_to_left
        }

        if(newTag == ConferenceListFragment.TAG && curTag == SearchFragmentList.TAG){
            return R.anim.exit_to_up
        }

        if(newTag == ConferenceListFragment.TAG && curTag == LoginFragment.TAG){
            return R.anim.exit_to_right
        }

        return R.anim.exit_to_up
    }

}