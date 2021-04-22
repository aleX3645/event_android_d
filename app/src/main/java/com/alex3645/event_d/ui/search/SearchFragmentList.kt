package com.alex3645.event_d.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alex3645.event_d.R
import com.alex3645.event_d.di.component.DaggerFragmentComponent
import com.alex3645.event_d.di.module.FragmentModule
import com.alex3645.event_d.model.Conference
import com.alex3645.event_d.model.Event
import com.alex3645.event_d.model.User
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.recycler_fragment.*
import kotlinx.android.synthetic.main.search_layout.*
import kotlinx.android.synthetic.main.search_layout.view.*
import javax.inject.Inject

class SearchFragmentList: Fragment(), SearchContract.View, SearchConferenceListAdapter.onItemClickListener {

    @Inject
    lateinit var presenter: SearchContract.Presenter

    private lateinit var rootView: View

    lateinit var conferenceAdapter: SearchConferenceListAdapter
    lateinit var eventAdapter: SearchEventListAdapter
    lateinit var userAdapter: SearchUserListAdapter

    fun newInstance(): SearchFragmentList {
        return SearchFragmentList()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            rootView.searchProgressBar.visibility = View.VISIBLE
        } else {
            rootView.searchProgressBar.visibility = View.GONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
        initEvents()
    }

    var selectedTabId: Int = 0
    fun initEvents(){

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    rootView.searchTextField.editText?.setText("")
                    when (tab.position) {
                        0 -> {
                            val r = rootView.searchRecyclerView
                            r.adapter = conferenceAdapter
                            selectedTabId = 0
                        }
                        1 -> {
                            val r = rootView.searchRecyclerView
                            r.adapter = eventAdapter
                            selectedTabId = 1
                        }
                        2 -> {
                            val r = rootView.searchRecyclerView
                            r.adapter = userAdapter
                            selectedTabId = 2
                        }
                    }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle tab reselect
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab unselect
            }
        })

        rootView.searchTextField.editText?.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                showProgress(true)

                when (selectedTabId) {
                    0 -> {
                        presenter.loadConferenceDataByText(searchTextField.editText!!.text.toString())
                        clearRecyclerData()
                    }
                    1 -> {
                        presenter.loadEventDataByText(searchTextField.editText!!.text.toString())
                        clearRecyclerData()
                    }
                    2 -> {
                        presenter.loadUserDataByText(searchTextField.editText!!.text.toString())
                        clearRecyclerData()
                    }
                }
            }
        })
    }

    private fun clearRecyclerData(){
        when (selectedTabId) {
            0 -> {
                (rootView.searchRecyclerView.adapter as SearchConferenceListAdapter).list.removeAll((rootView.searchRecyclerView.adapter as SearchConferenceListAdapter).list)
                (rootView.searchRecyclerView.adapter as SearchConferenceListAdapter).notifyDataSetChanged()
            }
            1 -> {
                (rootView.searchRecyclerView.adapter as SearchEventListAdapter).list.removeAll((rootView.searchRecyclerView.adapter as SearchEventListAdapter).list)
                (rootView.searchRecyclerView.adapter as SearchEventListAdapter).notifyDataSetChanged()
            }
            2 -> {
                (rootView.searchRecyclerView.adapter as SearchUserListAdapter).list.removeAll((rootView.searchRecyclerView.adapter as SearchUserListAdapter).list)
                (rootView.searchRecyclerView.adapter as SearchUserListAdapter).notifyDataSetChanged()
            }
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        rootView = inflater!!.inflate(R.layout.search_layout, container, false)

        conferenceAdapter = SearchConferenceListAdapter(activity!!, ArrayList())
        eventAdapter = SearchEventListAdapter(activity!!, ArrayList())
        userAdapter = SearchUserListAdapter(activity!!, ArrayList())

        val r = rootView.searchRecyclerView
        r.adapter = conferenceAdapter
        r.layoutManager = LinearLayoutManager(activity)
        return rootView
    }

    override fun showErrorMessage(error: String) {
        Log.d("!!!", error)
    }

    override fun loadConferenceDataSuccess(list: List<Conference>) {
        Log.d("test",list.size.toString())
        (rootView.searchRecyclerView.adapter as SearchConferenceListAdapter).list.addAll(list)
        (rootView.searchRecyclerView.adapter as SearchConferenceListAdapter).notifyDataSetChanged()
    }

    override fun loadEventDataSuccess(list: List<Event>) {
        Log.d("test",list.size.toString())
        (rootView.searchRecyclerView.adapter as SearchEventListAdapter).list.addAll(list)
        (rootView.searchRecyclerView.adapter as SearchEventListAdapter).notifyDataSetChanged()
    }

    override fun loadUserDataSuccess(list: List<User>) {
        Log.d("test",list.size.toString())
        (rootView.searchRecyclerView.adapter as SearchUserListAdapter).list.addAll(list)
        (rootView.searchRecyclerView.adapter as SearchUserListAdapter).notifyDataSetChanged()
    }

    override fun itemDetail(conferenceId: String) {
        TODO("Not yet implemented")
    }

    private fun injectDependency() {
        val listComponent = DaggerFragmentComponent.builder()
                .fragmentModule(FragmentModule())
                .build()

        listComponent.inject(this)
    }

    companion object {
        val TAG: String = "SearchListFragment"
    }
}