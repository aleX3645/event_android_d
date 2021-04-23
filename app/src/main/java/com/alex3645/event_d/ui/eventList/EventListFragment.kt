package com.alex3645.event_d.ui.eventList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.alex3645.event_d.R
import com.alex3645.event_d.di.component.DaggerFragmentComponent
import com.alex3645.event_d.di.module.FragmentModule
import com.alex3645.event_d.model.Conference
import com.alex3645.event_d.model.Event
import com.alex3645.event_d.ui.conference.ConferenceFragment
import com.alex3645.event_d.ui.conferencesList.ConferenceListAdapter
import com.alex3645.event_d.ui.conferencesList.ConferenceListContract
import com.alex3645.event_d.ui.conferencesList.ConferenceListFragment
import com.alex3645.event_d.ui.event.EventFragment
import com.alex3645.event_d.ui.login.LoginFragment
import com.alex3645.event_d.util.AnimationConsider
import kotlinx.android.synthetic.main.conference_layout.view.*
import kotlinx.android.synthetic.main.recycler_fragment.view.*
import javax.inject.Inject

class EventListFragment(private val conferenceId: Int): Fragment(), EventListContract.View, EventListAdapter.onItemClickListener {

    @Inject
    lateinit var presenter: EventListContract.Presenter

    private lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        rootView = inflater!!.inflate(R.layout.recycler_fragment, container, false)
        val r = rootView.recyclerView

        r.adapter = EventListAdapter(activity!!, ArrayList<Event>(), this)
        r.layoutManager = LinearLayoutManager(activity)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            rootView.progressBar.visibility = View.VISIBLE
        } else {
            rootView.progressBar.visibility = View.GONE
        }
    }

    override fun showErrorMessage(error: String) {
        Log.e("Error", error)
    }

    override fun loadEventsdataSuccess(list: List<Event>) {
        Log.d("!!!", list.toString())
        (rootView.recyclerView.adapter as EventListAdapter).list.addAll(list)
        (rootView.recyclerView.adapter as EventListAdapter).notifyDataSetChanged()
    }

    override fun itemDetail(conId: Int) {
        fragmentManager?.beginTransaction()
                ?.setCustomAnimations(AnimationConsider.considerEnterAnimation(ConferenceFragment.TAG, EventFragment.TAG),AnimationConsider.considerExitAnimation(ConferenceFragment.TAG, EventFragment.TAG))
                ?.disallowAddToBackStack()
                ?.replace(R.id.frame, EventFragment(conId), EventFragment.TAG)
                ?.commit()
    }

    private fun injectDependency() {
        val listComponent = DaggerFragmentComponent.builder()
                .fragmentModule(FragmentModule())
                .build()

        listComponent.inject(this)
    }

    private fun initView() {
        presenter.loadEventsByConferenceId(conferenceId)
    }

    companion object {
        val TAG: String = "EventListFragment"
    }
}