package com.alex3645.event_d.ui.event

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.alex3645.event_d.R
import com.alex3645.event_d.di.component.DaggerFragmentComponent
import com.alex3645.event_d.di.module.FragmentModule
import com.alex3645.event_d.model.Conference
import com.alex3645.event_d.model.Event
import com.alex3645.event_d.model.User
import com.alex3645.event_d.ui.conference.ConferenceContract
import com.alex3645.event_d.ui.conference.ConferenceFragment
import com.alex3645.event_d.ui.eventList.EventListFragment
import com.alex3645.event_d.ui.user.UserFragment
import com.alex3645.event_d.util.AnimationConsider
import kotlinx.android.synthetic.main.conference_layout.view.*
import kotlinx.android.synthetic.main.event_layout.view.*
import javax.inject.Inject

class EventFragment(val eventID: Int): Fragment(), EventContract.View {
    @Inject
    lateinit var presenter: EventContract.Presenter

    lateinit var eventData: Event
    private lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    private fun injectDependency() {
        val eventComponent = DaggerFragmentComponent.builder()
                .fragmentModule(FragmentModule())
                .build()

        eventComponent.inject(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        rootView = inflater!!.inflate(R.layout.event_layout, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()

        initEvents()
        initView()
    }

    private fun initEvents(){
        rootView.speakerButton.setOnClickListener {
            fragmentManager?.beginTransaction()
                    ?.setCustomAnimations(AnimationConsider.considerEnterAnimation(EventFragment.TAG, UserFragment.TAG),AnimationConsider.considerExitAnimation(EventFragment.TAG, UserFragment.TAG))
                    ?.disallowAddToBackStack()
                    ?.replace(R.id.frame, UserFragment(eventData.speaker_id), UserFragment.TAG)
                    ?.commit()
        }
    }

    private fun initView(){
        showProgress(true)
        presenter.loadEventById(eventID)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            rootView.eventProgressBar.visibility = View.VISIBLE
            rootView.visibility = View.GONE
        } else {
            rootView.eventProgressBar.visibility = View.GONE
            rootView.visibility = View.VISIBLE
        }
    }

    override fun showErrorMessage(error: String) {
        val toast = Toast.makeText(requireActivity(), error, Toast.LENGTH_LONG)
        Log.d("error", error)
        toast.show()
    }

    override fun showEventDataSuccess(eventData: Event) {
        this.eventData = eventData
        rootView.eventTitle.text = eventData.name
        rootView.eventStartDate.text = eventData.date_start.toString()
        rootView.eventEndDate.text = eventData.date_end.toString()
        rootView.eventDescription.text = eventData.description
    }

    companion object {
        val TAG: String = "EventFragment"
    }
}
